package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.SysTable;
import cn.hhnail.backend.mapper.SysTableMapper;
import cn.hhnail.backend.service.SysTableService;
import cn.hhnail.backend.vo.request.SysTableReqVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysTableServiceImpl implements SysTableService {

    @Autowired
    SysTableMapper sysTableMapper;


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

        BeanUtils.copyProperties(reqVO,entity);

        sysTableMapper.updateById(entity);
    }
}
