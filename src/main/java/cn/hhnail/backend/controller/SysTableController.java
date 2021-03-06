package cn.hhnail.backend.controller;

import cn.hhnail.backend.bean.SysTable;
import cn.hhnail.backend.service.SysTableService;
import cn.hhnail.backend.vo.response.SysTableRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
