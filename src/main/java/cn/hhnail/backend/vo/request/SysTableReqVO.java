package cn.hhnail.backend.vo.request;

import cn.hhnail.backend.bean.SysColumn;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class SysTableReqVO implements Serializable {

    private String chineseName;

    private String englishName;

    private Integer orderId;

    private List<SysColumn> columns = new ArrayList<>();

    private List<String> values = new ArrayList<>();


}
