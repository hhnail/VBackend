package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.FreeReport;
import cn.hhnail.backend.mapper.FreeReportMapper;
import cn.hhnail.backend.mapstruct.FreeReportAdapter;
import cn.hhnail.backend.service.FreeReportService;
import cn.hhnail.backend.vo.request.FreeReportReqVO;
import cn.hhnail.backend.vo.response.AntdTableColumn;
import cn.hhnail.backend.vo.response.FreeReportRespVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreeReportServiceImpl implements FreeReportService {

    @Autowired
    FreeReportMapper freeReportMapper;

    @Override
    public void saveFreeReport(FreeReportReqVO vo) {
        FreeReport entity = new FreeReport();
        BeanUtils.copyProperties(vo, entity);
        entity.setName(vo.getReportName());
        entity.setColumnsView(JSONObject.toJSONString(vo.getColumnsView()));
        freeReportMapper.insert(entity);
    }

    @Override
    public FreeReportRespVO getFreeReport(String id) {
        FreeReportRespVO result = new FreeReportRespVO();
        result.setReportName("");

        List<AntdTableColumn> tableColumns = new ArrayList<>();
        result.setViewColumns(tableColumns);
        // freeReportMapper

        return null;
    }
}
