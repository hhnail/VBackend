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
import java.io.IOException;
import java.io.InputStream;
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
}
