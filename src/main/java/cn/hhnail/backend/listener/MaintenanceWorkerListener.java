package cn.hhnail.backend.listener;

import cn.hhnail.backend.bean.MaintenanceWorker;
import cn.hhnail.backend.service.MaintenanceWorkerService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author r221587
 * @version 1.0
 * @description: 运维人员 导入 监听器
 * @date 2023/2/28 9:48
 */
@Slf4j
public class MaintenanceWorkerListener extends AnalysisEventListener<MaintenanceWorker> {

    private MaintenanceWorkerService maintenanceWorkerService;

    private List<MaintenanceWorker> list = new ArrayList<>();

    // 通过构造器注入mapper
    public MaintenanceWorkerListener(MaintenanceWorkerService maintenanceWorkerService) {
        this.maintenanceWorkerService = maintenanceWorkerService;
    }

    @Override
    public void invoke(MaintenanceWorker data, AnalysisContext context) {

        log.info("region = {}", data);
        list.add(data);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
    }

    private void saveData() {
        log.info("------------  开始存储数据  ----------");
        if (list == null && list.size() < 1) {
            throw new RuntimeException("数据为空");
        }
        for (MaintenanceWorker maintenanceWorker : list) {
            // TODO 存储db
            // maintenanceWorkerService.saveBatch(list);
        }
    }

}
