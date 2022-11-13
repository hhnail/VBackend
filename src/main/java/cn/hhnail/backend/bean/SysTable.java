package cn.hhnail.backend.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

import java.util.List;

@Data
@ToString
public class SysTable  implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String label;

    private String name;

    private Integer orderId;

    private Integer moduleId;

    private Integer deleted;
    @TableField(exist = false)
    private List<SysColumn> columns;


}
