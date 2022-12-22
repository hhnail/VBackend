package cn.hhnail.backend.mapper;

import java.util.List;
import java.util.Map;

public interface OrganizationMapper {

    List<Map<String, Object>> getLeafOrganization(String id);


}
