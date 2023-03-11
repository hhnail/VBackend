package cn.hhnail.backend.controller;


import cn.hhnail.backend.bean.DemoData;
import cn.hhnail.backend.bean.MaintenanceWorker;
import cn.hhnail.backend.listener.MaintenanceWorkerListener;
import cn.hhnail.backend.service.FileService;
import cn.hhnail.backend.service.MaintenanceWorkerService;
import cn.hhnail.backend.vo.response.AppResponse;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/vapi")
@Slf4j
public class FileController {

    @Autowired
    FileService fileService;
    @Autowired
    MaintenanceWorkerService maintenanceWorkerService;

    // 本地文件存储路径
    private final String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";


    /**
     * 获取Excel的sheet
     *
     * @param file
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getExcelSheetList")
    public AppResponse<Map<String, Object>> getExcelSheetList(@RequestParam("file") MultipartFile file) {

        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> sheetList = new ArrayList<>();

        try {
            InputStream inputStream = file.getInputStream();
            ExcelReader build = EasyExcel.read(inputStream).build();
            List<ReadSheet> readSheets = build.excelExecutor().sheetList();

            readSheets.forEach(item -> {
                Map<String, Object> map = new HashMap<>();
                Integer sheetNo = item.getSheetNo();
                String sheetName = item.getSheetName();
                map.put("sheetNo", sheetNo);
                map.put("sheetName", sheetName);
                sheetList.add(map);
            });

            data.put("sheetList", sheetList);
            return AppResponse.ok(data);
        } catch (Exception e) {
            log.info("FileController /vpi/getExcelSheetList error {}", e);
            return AppResponse.fail(null);
        }
    }


    @Deprecated
    @RequestMapping(method = RequestMethod.POST, value = "/uploadFile")
    public AppResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            ExcelReader build = EasyExcel.read(inputStream).build();
            List<ReadSheet> readSheets = build.excelExecutor().sheetList();
            return AppResponse.ok(null);
        } catch (Exception e) {
            log.info("FileController /vpi/uploadFile error {}", e);
            return AppResponse.fail(null);
        }
    }


    /**
     * 生成测试数据
     *
     * @return
     */
    private List<DemoData> data() {
        List<DemoData> testData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DemoData item = new DemoData();
            item.setString("第" + i + "行");
            item.setDoubleData((double) i);
            item.setDate(new Date());
            testData.add(item);
        }
        return testData;
    }


    /**
     * 写入Excel
     */
    @Test
    public void simpleWrite() {
        String fileName = filePath + "test.xlsx";
        EasyExcel.write(fileName, DemoData.class)
                .sheet("模板")
                .doWrite(data());
    }


    @RequestMapping(method = RequestMethod.POST, value = "/saveFile")
    public AppResponse<String> saveFile(@RequestParam("file") MultipartFile file) {
        try {
            fileService.saveFile(filePath, file);
            return AppResponse.ok(null);
        } catch (Exception e) {
            log.error("saveFile:{}", e);
            return AppResponse.fail(null);
        }
    }


    /**
     * 导出Excel
     * 文件下载
     */
    @ApiOperation(value = "导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<DemoData> data = data();
        exportExcel(response, "用户统计数据", DemoData.class, data);
    }


    /**
     * 导出excel
     *
     * @param response 相应response
     * @param title    保存文件的标题
     * @param head     保存excel对象的实体类
     * @param list     需要保存的数据列表
     * @throws IOException 异常捕获
     */
    public void exportExcel(HttpServletResponse response, String title, Class head, List list) throws IOException {
        exportExcel(response, title, head, list, null);

    }

    public void exportExcel(HttpServletResponse response, String title, Class head, List list, Set<String> set) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(title, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        if (set == null) {
            EasyExcel.write(response.getOutputStream(), head).sheet("模板")
                    .doWrite(list);
        } else {
            EasyExcel.write(response.getOutputStream(), head).includeColumnFiledNames(set).sheet("模板")
                    .doWrite(list);
        }

    }


    /**
     * 导入
     */
    @ApiOperation(value = "导入")
    @PostMapping("/import")
    public void importData(@RequestBody MultipartFile file) throws Exception {
        if (file == null) {
            throw new RuntimeException("file不得为空");
        }

        EasyExcel.read(file.getInputStream(),
                MaintenanceWorker.class,
                new MaintenanceWorkerListener(maintenanceWorkerService)
        ).sheet().doRead();
    }


    /**
     * 保存Base64格式的图片
     * 图片转base64的工具网站：https://c.runoob.com/front-end/59/
     * application/json
     * {
     * "base64":"data:image/png;base64,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"
     * }
     */
    @ApiOperation(value = "保存Base64格式的图片")
    @PostMapping("/saveBase64Img")
    public String saveBase64Img(@RequestBody String base64) {
        // 解密
        try {
            // 去掉base64前缀 data:image/jpeg;base64,
            base64 = base64.substring(base64.indexOf(",", 1) + 1, base64.length() - 2);
            // 解密，解密的结果是一个byte数组
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] imgbytes = decoder.decode(base64);
            for (int i = 0; i < imgbytes.length; ++i) {
                if (imgbytes[i] < 0) {
                    imgbytes[i] += 256;
                }
            }
            String filePath = "C:\\Users\\R221587\\Desktop\\";
            String fileName = "pushImg.jpg"; // 文件名如果一样的话，会覆盖掉
            String imgClassPath = filePath + fileName;
            // String imgClassPath = "C:\\Users\\R221587\\Desktop\\" + UUID.randomUUID().toString() + ".jpg";
            // 保存图片
            OutputStream out = new FileOutputStream(imgClassPath);
            // 写入图片数据
            out.write(imgbytes);
            out.flush();
            out.close();
            // 返回图片的相对路径 = 图片分类路径+图片名+图片后缀
            return imgClassPath;
        } catch (IOException e) {
            return null;
        }
    }

}
