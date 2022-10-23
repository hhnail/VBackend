package cn.hhnail.backend.mapper;

import cn.hhnail.backend.bean.FreeReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface FreeReportMapper extends BaseMapper<FreeReport> {

    /**
     * 测试mybatis
     */
    List<Map<String,Object>> test();

    /**
     *
     */
    List<Map<String,Object>> queryByMap(Map<String, Object> queryMap);


}
