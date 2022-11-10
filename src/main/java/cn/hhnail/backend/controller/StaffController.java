package cn.hhnail.backend.controller;

import cn.hhnail.backend.service.StaffService;
import cn.hhnail.backend.util.EncryptUtil;
import cn.hhnail.backend.vo.request.UserReqVO;
import cn.hhnail.backend.vo.response.AppResponse;
import cn.hhnail.backend.vo.response.UserRespVO;
import cn.hhnail.backend.vo.response.VResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vapi")
public class StaffController {

    @Autowired
    StaffService staffService;


    @PostMapping("/login")
    public AppResponse<UserRespVO> login(UserReqVO reqVO) {

        boolean success = staffService.login(reqVO);
        if (success) {
            reqVO.setToken(EncryptUtil.getJwtToken());
        }

        UserRespVO respVO = new UserRespVO();
        BeanUtils.copyProperties(reqVO, respVO);
        return AppResponse.ok(respVO);

    }


    @PostMapping("/getStaffList")
    public AppResponse<VResult> getStaffList() {
        List<Map<String, Object>> staffList = staffService.getStaffList();
        VResult result = new VResult();
        result.setDataSource(staffList);
        return AppResponse.ok(result);
    }

}
