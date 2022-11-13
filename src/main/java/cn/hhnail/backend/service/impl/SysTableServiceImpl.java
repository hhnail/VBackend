package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.SysColumn;
import cn.hhnail.backend.bean.SysTable;
import cn.hhnail.backend.enums.TableType;
import cn.hhnail.backend.mapper.SysColumnMapper;
import cn.hhnail.backend.mapper.SysTableMapper;
import cn.hhnail.backend.service.SysTableService;
import cn.hhnail.backend.util.StringUtils;
import cn.hhnail.backend.vo.request.SysTableReqVO;
import cn.hhnail.backend.vo.response.SysTableRespVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysTableServiceImpl implements SysTableService {

    @Autowired
    SysTableMapper sysTableMapper;
    @Autowired
    SysColumnMapper columnMapper;


    @Override
    public List<SysTable> getTables() {
        List<SysTable> sysTables = sysTableMapper.selectList(null);
        return sysTables;
    }

    @Override
    public void createTable(SysTableReqVO reqVO) {
        // 记录当前执行到哪一步。用于标记回滚点
        int currentStep = 0;
        try {
            // 1、建表
            sysTableMapper.createTable(reqVO);
            currentStep++;
            // 2、维护元表信息
            SysTable tableEntity = new SysTable();
            BeanUtils.copyProperties(reqVO, tableEntity);
            sysTableMapper.insert(tableEntity);
            // sysTableMapper.saveTableInfo(reqVO);
            currentStep++;
            // 3、维护字段表信息
            // 注入自增id
            reqVO.setId(tableEntity.getId());
            sysTableMapper.saveFieldsInfo(reqVO);
            currentStep++;
            // 【4、插入初始化数据】
            // sysTableMapper.saveData(reqVO);
        } catch (Exception e) {
            e.printStackTrace();
            // 创建表的操作不能回滚，需要手动回滚
            // 1、删除表
            dropTable(reqVO.getName());
            if (currentStep > 0) {
                // 2、删除元表信息
                QueryWrapper qw = new QueryWrapper();
                qw.eq("name", reqVO.getName());
                sysTableMapper.delete(qw);
            }
            //
            // if(currentStep > 1){
            //
            // }
            // 手动抛出触发@Transactional的回滚
            throw new RuntimeException("");
        }
    }

    @Override
    public List<SysTable> selectTables() {
        return sysTableMapper.selectTables();
    }

    @Override
    public void dropTable(String tableName) {
        sysTableMapper.dropTable(tableName);
    }

    @Override
    public void updateTable(SysTableReqVO reqVO) {
        SysTable entity = new SysTable();

        BeanUtils.copyProperties(reqVO, entity);

        // 更新逻辑表信息
        sysTableMapper.updateById(entity);

        // 更新物理表信息
        String oldName = reqVO.getOldName();
        String newName = reqVO.getName();
        if (StringUtils.notEmpty(oldName) && !oldName.equals(newName)) {
            sysTableMapper.alterTableName(oldName, newName);
        }

    }

    @Override
    public List<SysColumn> getTableColumns(String id) {
        QueryWrapper<SysColumn> queryOption = new QueryWrapper<>();
        queryOption.eq("sys_table_id", id);
        List<SysColumn> sysColumns = columnMapper.selectList(queryOption);
        return sysColumns;
    }

    @Override
    public List<SysTableRespVO> getCodeTable(String type) {
        List<SysTableRespVO> vos = new ArrayList<>();

        QueryWrapper queryOption = new QueryWrapper();
        queryOption.eq(TableType.mapDbColumn, type);
        List<SysTable> list = sysTableMapper.selectList(queryOption);
        list.forEach(item->{
           SysTableRespVO respVO = new SysTableRespVO();
           BeanUtils.copyProperties(item,respVO);
           vos.add(respVO);
        });

        // 获取表结构
        // 获取数据

        return vos;
    }

}
