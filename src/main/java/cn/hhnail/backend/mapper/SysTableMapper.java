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

    void dropTable(@Param("tableName") String tableName);
}
