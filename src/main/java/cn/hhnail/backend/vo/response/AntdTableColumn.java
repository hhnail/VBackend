package cn.hhnail.backend.vo.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AntdTableColumn implements Serializable {

    private String title;

    private String dataIndex;

    private String columnCode;

    private String key;

    private Integer width;

}
