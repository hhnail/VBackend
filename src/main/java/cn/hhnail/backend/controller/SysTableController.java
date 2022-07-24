package cn.hhnail.backend.controller;

import cn.hhnail.backend.bean.SysColumn;
import cn.hhnail.backend.bean.SysTable;
import cn.hhnail.backend.service.ComService;
import cn.hhnail.backend.service.SysTableService;
import cn.hhnail.backend.util.StringUtils;
import cn.hhnail.backend.bean.QueryOption;
import cn.hhnail.backend.vo.request.SysTableReqVO;
import cn.hhnail.backend.vo.response.AppResponse;
import cn.hhnail.backend.vo.response.SysTableRespVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vapi")
public class SysTableController {

    Logger logger = LoggerFactory.getLogger(SysTableController.class);


    @Autowired
    SysTableService sysTableService;
    @Autowired
    ComService comService;

    @PostMapping(value = "/getTables")
    public List<SysTableRespVO> getTables() {
        List<SysTable> list = sysTableService.getTables();
        List<SysTableRespVO> vos = new ArrayList<>();
        list.forEach(item -> {
            SysTableRespVO vo = new SysTableRespVO();
            BeanUtils.copyProperties(item, vo);
            vos.add(vo);
        });
        return vos;
    }

    @Transactional
    @PostMapping(value = "/createTable")
    public AppResponse<String> createTable(@RequestBody SysTableReqVO reqVO) {
        try {
            String name = reqVO.getName();
            // 参数校验
            if (name == null || StringUtils.isEmpty(name)) {
                throw new RuntimeException("表英文名不得为空");
            }
            // 截取两端空格，否则sql会报错
            reqVO.setName(reqVO.getName().trim());
            reqVO.getColumns().forEach(column -> {
                column.setName(column.getName().trim());
            });

            sysTableService.createTable(reqVO);
            return AppResponse.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            AppResponse<String> fail = AppResponse.fail();
            fail.setMsg(e.getMessage());
            return fail;
        }
    }

    @Transactional
    @PostMapping(value = "/updateTable")
    public AppResponse<String> updateTable(@RequestBody SysTableReqVO reqVO) {
        try {
            String name = reqVO.getName();
            // 参数校验
            if (name == null || StringUtils.isEmpty(name)) {
                throw new RuntimeException("表英文名不得为空");
            }
            sysTableService.updateTable(reqVO);
            return AppResponse.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            AppResponse<String> fail = AppResponse.fail();
            fail.setMsg(e.getMessage());
            return fail;
        }
    }

    @PostMapping(value = "/selectTables")
    public AppResponse<String> selectTables() {
        sysTableService.selectTables();
        return AppResponse.ok(null);
    }


    @PostMapping(value = "/getTableColumns")
    public AppResponse<List<SysColumn>> getTableColumns(
            @RequestParam("id") String id
    ) {
        logger.info("id={}", id);
        List<SysColumn> columns = sysTableService.getTableColumns(id);
        return AppResponse.ok(columns);
    }


    /**
     * 获取码表
     */
    @PostMapping(value = "/getCodeTable")
    public AppResponse<List<SysTableRespVO>> getCodeTable(@RequestBody QueryOption queryOption) {
        logger.info("表类型（单级编码或多级编码）={}", queryOption);
        // String type = params.get("type").toString();
        // List<SysTableRespVO> tables = sysTableService.getCodeTable(type);
        // return AppResponse.ok(tables);
        List<SysTableRespVO> tables = comService.select(queryOption);
        return AppResponse.ok(tables);
    }


}
