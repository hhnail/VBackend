package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.mapper.OrganizationMapper;
import cn.hhnail.backend.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author r221587
 * @version 1.0
 * @description: 组织
 * @dat022/12/22 16:01
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    public List<Map<String, Object>> getLeafOrganization(String id) {
        return organizationMapper.getLeafOrganization(id);
    }

}
