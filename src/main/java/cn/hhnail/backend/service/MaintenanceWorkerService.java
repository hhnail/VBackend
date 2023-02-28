package cn.hhnail.backend.service;


import cn.hhnail.backend.bean.MaintenanceWorker;

import java.util.List;
import java.util.Map;

/**
 * @author phv
 * @date 2023-02-21 09:52:33
 */
public interface MaintenanceWorkerService{
    /**
     * 列表
     *
     * @param params
     * @return
     */
    List<MaintenanceWorker> findList(Map<String, Object> params);
}

