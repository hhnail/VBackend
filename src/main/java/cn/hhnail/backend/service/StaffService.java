package cn.hhnail.backend.service;

import cn.hhnail.backend.vo.request.UserReqVO;

public interface StaffService {


    boolean login(UserReqVO reqVO);

}
