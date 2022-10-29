package cn.hhnail.backend.controller;


import cn.hhnail.backend.bean.DemoData;
import cn.hhnail.backend.service.FileService;
import cn.hhnail.backend.vo.response.AppResponse;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/vapi")
@Slf4j
public class FileController {

    @Autowired
    FileService fileService;
    // robam笔记本
    // private final String filePath = "D:\\workspace\\vSrc\\VBackend\\static\\";
    // 雷神
    private final String filePath = "D:\\workspace\\java\\src\\idea_workspace\\VBackend\\static";


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
        String fileName = "D:\\" + "test.xlsx";
        EasyExcel.write(fileName, DemoData.class)
                .sheet("模板")
                .doWrite(data());
    }
}
