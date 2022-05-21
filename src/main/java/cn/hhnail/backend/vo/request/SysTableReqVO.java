package cn.hhnail.backend.vo.request;

import cn.hhnail.backend.bean.SysColumn;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class SysTableReqVO implements Serializable {

    private Integer id;

    private String label;

    private String name;

    private Integer orderId;

    private Integer moduleId;

    private String remark;

    // private Integer sysTableId;

    private List<SysColumn> columns = new ArrayList<>();

    private List<String> values = new ArrayList<>();


}
