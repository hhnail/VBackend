package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.FreeReport;
import cn.hhnail.backend.mapper.FreeReportMapper;
import cn.hhnail.backend.service.FreeReportService;
import cn.hhnail.backend.vo.request.FreeReportReqVO;
import cn.hhnail.backend.vo.response.AntdTableColumn;
import cn.hhnail.backend.vo.response.FreeReportRespVO;
import cn.hhnail.backend.vo.request.VQueryRule;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


        // 数据
        // Map<String,Object> queryMap = new HashMap<>();
        // queryMap.put("primaryTable", dObj.getPrimaryTable());
        // queryMap.put("queryColumns", tableColumns);
        // queryMap.put("conditions", null);
        // List<Map<String,Object>> data = freeReportMapper.queryByMap(queryMap);

        VQueryRule query = new VQueryRule();
        query.setSql(dObj.getReportSql());
        query.setQueryColumns(tableColumns);
        List<Map<String,Object>> data = freeReportMapper.queryByQueryRule(query);

        result.setReportName(dObj.getName());
        result.setViewColumns(tableColumns);
        result.setViewData(data);
        return result;
    }

    @Override
    public List<FreeReportRespVO> getFreeReportList() {
        QueryWrapper<FreeReport> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0);
        List<FreeReport> freeReports = freeReportMapper.selectList(wrapper);

        List<FreeReportRespVO> respVOList = new ArrayList<>();
        freeReports.forEach(item->{
            FreeReportRespVO vo = new FreeReportRespVO();
            BeanUtils.copyProperties(item,vo);
            vo.setReportName(item.getName());
            respVOList.add(vo);
        });
        return respVOList;
    }
}
