package cn.hhnail.backend.mapper;

import cn.hhnail.backend.bean.FreeReport;
import cn.hhnail.backend.vo.request.VQueryRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface FreeReportMapper extends BaseMapper<FreeReport> {

    /**
     * 测试mybatis
     */
    List<Map<String,Object>> test();

    /**
     * 根据map动态查询字段
     */
    List<Map<String,Object>> queryByMap(Map<String, Object> queryMap);

    /**
     * 根据【查询规则】动态查询字段
     */
    List<Map<String, Object>> queryByQueryRule(VQueryRule query);
}
