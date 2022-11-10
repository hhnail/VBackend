package cn.hhnail.backend.vo.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
public class VResult {

    private List<Map<String, Object>> dataSource;

    private List<Map<String, Object>> columns;


}
