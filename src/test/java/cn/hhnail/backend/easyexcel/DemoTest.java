package cn.hhnail.backend.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemoTest {

    private final String filePath = System.getProperty("user.dir")+"\\static\\";

    @Test
    public void simpleRead() {
        String fileName = "D:\\" + "test.xlsx";
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener())
                .sheet()
                .doRead();
    }


    /**
     * 写入Excel
     */
    @Test
    public void simpleWrite() {
        String fileName = filePath + "test.xlsx";
        EasyExcel.write(fileName, cn.hhnail.backend.bean.DemoData.class)
                .sheet("模板")
                .doWrite(data());
    }

    /**
     * 生成测试数据
     *
     * @return
     */
    private List<cn.hhnail.backend.bean.DemoData> data() {
        List<cn.hhnail.backend.bean.DemoData> testData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            cn.hhnail.backend.bean.DemoData item = new cn.hhnail.backend.bean.DemoData();
            item.setString("第" + i + "行");
            item.setDoubleData((double) i);
            item.setDate(new Date());
            testData.add(item);
        }
        return testData;
    }



    @Test
    public void test01(){
        System.out.println(System.getProperty("user.dir")+"\\static\\");
    }
}
