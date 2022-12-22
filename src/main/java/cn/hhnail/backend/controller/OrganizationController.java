package cn.hhnail.backend.controller;

import cn.hhnail.backend.service.OrganizationService;
import cn.hhnail.backend.vo.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author r221587
 * @version 1.0
 * @description: 组织
 * @date 2022/12/22 15:44
 */
@RestController
@RequestMapping("/vapi")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @PostMapping("/getLeafOrganization")
    public Map<String, Object> getLeafOrganization(@RequestBody Map<String, Object> param) {
        if (param == null) {
            throw new RuntimeException("param不得为空");
        }
        if (param.get("id") == null || param.get("id").toString().trim().isEmpty()) {
            throw new RuntimeException("param不得为空");
        }

        String id = param.get("id").toString();


        List<Map<String, Object>> voList = organizationService.getLeafOrganization(id);

        // 统一返回格式
        return new ResponseResult<List<Map<String, Object>>>()
                .setData(voList)
                .setDescription("操作成功")
                .setSuccess();
    }


}
