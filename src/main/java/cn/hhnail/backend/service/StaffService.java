package cn.hhnail.backend.service;

import cn.hhnail.backend.vo.request.UserReqVO;

import java.util.List;
import java.util.Map;


public interface StaffService {

    boolean login(UserReqVO reqVO);

    List<Map<String, Object>> getStaffList();

}
