package cn.hhnail.backend.controller;

import cn.hhnail.backend.enums.SystemMessage;
import cn.hhnail.backend.enums.SystemVariable;
import cn.hhnail.backend.service.FreeReportService;
import cn.hhnail.backend.util.VStringUtil;
import cn.hhnail.backend.vo.request.FreeReportReqVO;
import cn.hhnail.backend.vo.response.AntdTableColumn;
import cn.hhnail.backend.vo.response.AppResponse;
import cn.hhnail.backend.vo.response.FreeReportRespVO;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/vapi")
public class FreeReportController {

    @Autowired
    FreeReportService freeReportService;

    @PostMapping("/saveFreeReport")
    public AppResponse<String> saveFreeReport(@RequestBody Map<String, Object> paramMap) {
        // 1、解析参数
        JSONObject params = new JSONObject(paramMap);
        String reportName = params.getString("name");
        Integer moduleId = params.getInteger("moduleId");
        String description = params.getString("description");
        String reportSql = params.getString("reportSql");
        String primaryTable = "";
        // 通过sql解析主表
        String[] sqlFragment = reportSql.split(" ");
        for (int i = 0; i < sqlFragment.length; i++) {
            if ("from".equals(sqlFragment[i])) {
                primaryTable = sqlFragment[i + 1];
            }
        }

        // 参数校验
        if (VStringUtil.isEmpty(primaryTable)) {
            throw new RuntimeException(SystemMessage.PRIMARY_TABLE_CAN_NOT_NULL.getMessage());
        }

        // 解析字段
        int size = params.size();
        int columnMapSize = (size - 4) / 2;
        List<AntdTableColumn> columnsView = new ArrayList<>();
        for (int i = 0; i < columnMapSize; i++) {
            AntdTableColumn column = new AntdTableColumn();
            String columnName = params.getString(SystemVariable.COLUMN_NAME_PREFIX.getCode() + i);
            String columnLabel = params.getString(SystemVariable.COLUMN_LABEL_PREFIX.getCode() + i);
            column.setKey(columnName);
            column.setDataIndex(columnName);
            column.setTitle(columnLabel);
            columnsView.add(column);
        }
        FreeReportReqVO vo = new FreeReportReqVO(
                reportName,
                moduleId,
                description,
                reportSql,
                primaryTable,
                columnsView
        );
        // 2、xxxx
        freeReportService.saveFreeReport(vo);
        // 3、xxxx
        return AppResponse.ok(null);
    }

    @PostMapping("/getFreeReport")
    public AppResponse<FreeReportRespVO> getFreeReport(@RequestParam String id) {
        FreeReportRespVO respVO = freeReportService.getFreeReport(id);
        return AppResponse.ok(respVO);
    }

}
