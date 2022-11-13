package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.SysColumn;
import cn.hhnail.backend.bean.SysTable;
import cn.hhnail.backend.mapper.ComMapper;
import cn.hhnail.backend.mapper.SysTableMapper;
import cn.hhnail.backend.service.ComService;
import cn.hhnail.backend.bean.QueryOption;
import cn.hhnail.backend.vo.response.SysTableRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComServiceImpl implements ComService {

    @Autowired
    ComMapper comMapper;
    @Autowired
    SysTableMapper sysTableMapper;

    @Override
    public List<SysTableRespVO> select(QueryOption queryOption) {
        return null;
    }

    @Override
    public List<Object> test() {
        List<Object> test = comMapper.test();
        return test;
    }

    @Override
    @Transactional
    public void addField(SysColumn field) {
        SysTable sysTable = sysTableMapper.selectById(field.getSysTableId());
        field.setSysTableName(sysTable.getName());
        //1、添加逻辑字段
        comMapper.addLogicalField(field);
        // 2、添加物理字段
        comMapper.addPhysicsField(
                field.getSysTableName(),
                field.getName(),
                field.getType(),
                field.getLength());
    }
}
