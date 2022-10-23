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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        FreeReport dObj = freeReportMapper.selectById(id);
        if (dObj == null) {
            throw new RuntimeException(String.format("对应id：【%s】记录不存在"));
        }
        List<AntdTableColumn> tableColumns = JSONObject.parseArray(dObj.getColumnsView(), AntdTableColumn.class);


        // 列规则
        FreeReportRespVO result = new FreeReportRespVO();
        BeanUtils.copyProperties(dObj, result);
        result.setReportName(dObj.getName());
        result.setViewColumns(tableColumns);

        // 数据
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("primaryTable", dObj.getPrimaryTable());
        queryMap.put("queryColumns", tableColumns);
        queryMap.put("conditions", null);
        // List<Map<String,Object>> data0 = freeReportMapper.test();
        List<Map<String,Object>> data = freeReportMapper.queryByMap(queryMap);
        result.setViewData(data);

        return result;
    }
}
