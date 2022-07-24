package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.mapper.ComMapper;
import cn.hhnail.backend.service.ComService;
import cn.hhnail.backend.bean.QueryOption;
import cn.hhnail.backend.vo.response.SysTableRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComServiceImpl implements ComService {

    @Autowired
    ComMapper comMapper;

    @Override
    public List<SysTableRespVO> select(QueryOption queryOption) {
        return null;
    }

    @Override
    public List<Object> test() {
        List<Object> test = comMapper.test();
        return test;
    }
}
