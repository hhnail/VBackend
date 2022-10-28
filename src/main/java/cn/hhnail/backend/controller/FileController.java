package cn.hhnail.backend.controller;


import cn.hhnail.backend.bean.DemoData;
import cn.hhnail.backend.vo.response.AppResponse;
import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vapi")
public class FileController {

    @RequestMapping(method = RequestMethod.POST, value = "/uploadFile")
    public AppResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {

        System.out.println(file);

        return AppResponse.ok(null);
    }


    /**
     * 生成测试数据
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
