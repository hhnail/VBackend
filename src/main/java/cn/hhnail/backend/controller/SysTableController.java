package cn.hhnail.backend.controller;

import cn.hhnail.backend.bean.SysColumn;
import cn.hhnail.backend.bean.SysTable;
import cn.hhnail.backend.service.SysTableService;
import cn.hhnail.backend.util.StringUtils;
import cn.hhnail.backend.vo.request.SysTableReqVO;
import cn.hhnail.backend.vo.response.AppResponse;
import cn.hhnail.backend.vo.response.SysTableRespVO;
import com.sun.deploy.ref.AppRef;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vapi")
public class SysTableController {

    @Autowired
    SysTableService sysTableService;

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
            // @RequestParam("id") Integer id
    ) {
        // List<SysColumn> columns = sysTableService.getTableColumns(id);
        // return AppResponse.ok(columns);
        return null;
    }


}
