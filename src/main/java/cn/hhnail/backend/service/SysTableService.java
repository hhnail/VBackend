package cn.hhnail.backend.service;

import cn.hhnail.backend.bean.SysColumn;
import cn.hhnail.backend.bean.SysTable;
import cn.hhnail.backend.vo.request.SysTableReqVO;

import java.util.List;

public interface SysTableService {
	List<SysTable> getTables();

    /**
     * 创建表
     * @param reqVO （表信息、字段、初始化数据）
     */
    void createTable(SysTableReqVO reqVO);

    /**
     * 测试mybatis
     * @return
     */
    @Deprecated
    List<SysTable> selectTables();

    void dropTable(String tableName);

    void updateTable(SysTableReqVO reqVO);

    List<SysColumn> getTableColumns(String id);
}
