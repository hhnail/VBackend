package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.MaintenanceWorker;
import cn.hhnail.backend.service.MaintenanceWorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author phv
 * @date 2023-02-21 09:52:33
 */
@Slf4j
@Service
public class MaintenanceWorkerServiceImpl implements MaintenanceWorkerService {
    /**
     * 列表
     *
     * @param params
     * @return
     */
    @Override
    public List<MaintenanceWorker> findList(Map<String, Object> params) {
        // Page<MaintenanceWorker> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        // List<MaintenanceWorker> list = baseMapper.findList(page, params);
        // return PageResult.<MaintenanceWorker>builder().data(list).resp_code(0).count(page.getTotal()).build();
        return null;
    }
}
