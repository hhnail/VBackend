package cn.hhnail.backend.service;

import java.util.List;
import java.util.Map;

public interface OrganizationService {

    /**
     * 获取该节点下所有层级的叶子节点
     *
     * @param id
     * @return
     */
    List<Map<String, Object>> getLeafOrganization(String id);

}
