package cn.hhnail.backend.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class SysTable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer deleted;
    @TableField(exist = false)
    private List<SysColumn> columns;


}
