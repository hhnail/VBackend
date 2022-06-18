package cn.hhnail.backend.mapper;

import cn.hhnail.backend.bean.SysTable;
import cn.hhnail.backend.bean.Test;
import cn.hhnail.backend.vo.request.SysTableReqVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysTableMapper extends BaseMapper<SysTable> {

    @Deprecated
    List<SysTable> selectTables();

    void createTable(SysTableReqVO reqVO);

    void saveTableInfo(SysTableReqVO reqVO);

    void saveFieldsInfo(SysTableReqVO reqVO);

    /**
     * 删除表（物理删除）
     *
     * @param tableName 表名称
     */
    void dropTable(@Param("tableName") String tableName);

    /**
     * 修改表名称
     *
     * @param oldName 原名称
     * @param newName 新名称
     */
    void alterTableName(@Param("oldName") String oldName, @Param("newName") String newName);
}
