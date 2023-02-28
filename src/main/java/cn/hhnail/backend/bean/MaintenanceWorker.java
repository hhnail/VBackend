package cn.hhnail.backend.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author phv
 * @date 2023-02-21 09:52:33
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("maintenance_worker")
public class MaintenanceWorker {
    private static final long serialVersionUID = 1L;

    @TableId
    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("手机号")
    private String phone;

    @ExcelProperty("状态")
    private String state;

    @ExcelProperty("服务商编码")
    private String service;
    @ExcelProperty("服务商名称")
    private String serviceName;

    @ExcelProperty("备注")
    private String remarks;

    @TableField(fill = FieldFill.INSERT)
    @ExcelProperty("创建日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ExcelProperty("更新日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
