package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.service.ComService;
import cn.hhnail.backend.vo.request.QueryOption;
import cn.hhnail.backend.vo.response.SysTableRespVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComServiceImpl implements ComService {


    @Override
    public List<SysTableRespVO> select(QueryOption queryOption) {
        return null;
    }
}
