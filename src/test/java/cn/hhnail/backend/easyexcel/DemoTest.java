package cn.hhnail.backend.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

public class DemoTest {
    @Test
    public void simpleRead() {
        String fileName = "D:\\" + "test.xlsx";
        EasyExcel.read(fileName,DemoData.class,new DemoDataListener()).sheet().doRead();
    }
}
