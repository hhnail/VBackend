package cn.hhnail.backend.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SysTable  implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String chineseName;

    private String englishName;

    private Integer orderId;

    private Integer deleted;


}
