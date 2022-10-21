package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.mapper.CommonMapper;
import cn.hhnail.backend.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    CommonMapper commonMapper;

    @Override
    public Map<String, Object> executeSelect(String sql) {
        return null;
    }
}
