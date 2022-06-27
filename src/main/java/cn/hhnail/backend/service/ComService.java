package cn.hhnail.backend.service;

import cn.hhnail.backend.vo.request.QueryOption;
import cn.hhnail.backend.vo.response.SysTableRespVO;

import java.util.List;

public interface ComService {

    /**
     * 根据QueryOption查询
     */
    List<SysTableRespVO> select(QueryOption queryOption);
}
