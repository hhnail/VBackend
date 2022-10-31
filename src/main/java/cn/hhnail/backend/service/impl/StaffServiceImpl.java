package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.service.StaffService;
import cn.hhnail.backend.vo.request.UserReqVO;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {


    @Override
    public boolean login(UserReqVO reqVO) {
        return true;
    }


}
