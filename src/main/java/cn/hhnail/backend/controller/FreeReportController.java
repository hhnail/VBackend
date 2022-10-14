package cn.hhnail.backend.controller;

import cn.hhnail.backend.service.FreeReportService;
import cn.hhnail.backend.vo.response.AppResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/vapi")
public class FreeReportController {

    @Autowired
    FreeReportService freeReportService;

    @PostMapping("/saveFreeReport")
    public AppResponse<String> saveFreeReport(@RequestBody Map<String,Object> paramMap){
        // freeReportService.saveFreeReport();
        JSONObject params = new JSONObject(paramMap);
        return AppResponse.ok(null);
    }
}
