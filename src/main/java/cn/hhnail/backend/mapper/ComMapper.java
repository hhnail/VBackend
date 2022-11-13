package cn.hhnail.backend.mapper;

import cn.hhnail.backend.bean.SysColumn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComMapper {

    List<Object> test();

    /**
     * 添加逻辑字段
     */
    void addLogicalField(SysColumn field);

    /**
     * 添加物理字段
     */
    void addPhysicsField(
            @Param("sysTableName") String sysTableName,
            @Param("name") String name,
            @Param("type") String type,
            @Param("length") Integer length
    );

}
