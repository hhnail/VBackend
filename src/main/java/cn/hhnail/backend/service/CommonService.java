package cn.hhnail.backend.service;

import java.util.Map;

public interface CommonService {

    /**
     * 执行sql并返回结果
     */
    Map<String, Object> executeSelect(String sql);


}
