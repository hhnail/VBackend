package cn.hhnail.backend.service;

import cn.hhnail.backend.bean.QueryOption;
import cn.hhnail.backend.bean.SysColumn;
import cn.hhnail.backend.vo.response.SysTableRespVO;

import java.util.List;

public interface ComService {

    /**
     * 根据QueryOption查询
     */
    List<SysTableRespVO> select(QueryOption queryOption);

    @Deprecated
    List<Object> test();

    /**
     * 添加字段（逻辑、物理）
     */
    void addField(SysColumn field);
}
