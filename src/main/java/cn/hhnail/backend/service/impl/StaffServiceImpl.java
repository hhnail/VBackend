package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.mapper.StaffMapper;
import cn.hhnail.backend.service.StaffService;
import cn.hhnail.backend.vo.request.UserReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;


    @Override
    public boolean login(UserReqVO reqVO) {
        return true;
    }

    @Override
    public List<Map<String, Object>> getStaffList() {
        return staffMapper.getStaffList();
    }


}
