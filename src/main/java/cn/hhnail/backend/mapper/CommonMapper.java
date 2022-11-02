package cn.hhnail.backend.mapper;

import cn.hhnail.backend.vo.request.VQueryRule;

import java.util.List;
import java.util.Map;

public interface CommonMapper {

    /**
     * 根据【查询规则】动态查询字段
     */
    List<Map<String, Object>> queryByQueryRule(VQueryRule query);




}
