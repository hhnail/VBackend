package cn.hhnail.backend.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysTable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer deleted;


}
