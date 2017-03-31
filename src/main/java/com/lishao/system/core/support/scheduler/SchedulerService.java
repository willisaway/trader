package com.lishao.system.core.support.scheduler;

import java.util.List;
import java.util.Map;

import com.lishao.system.core.base.BaseProviderImpl;
import com.lishao.system.core.mybatis.Page;
import com.lishao.system.core.util.InstanceUtil;
import com.lishao.system.dao.scheduler.TaskFireLogMapper;
import com.lishao.system.model.scheduler.TaskFireLog;
import com.lishao.system.model.scheduler.TaskScheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author ShenHuaJie
 * @version 2016年7月1日 上午11:34:59
 */
public class SchedulerService {
    @Autowired
    private TaskFireLogMapper logMapper;
    @Autowired
    private SchedulerManager schedulerManager;

    // 获取所有作业
    public List<TaskScheduled> getAllTaskDetail() {
        return schedulerManager.getAllJobDetail();
    }

    // 执行作业
    public void execTask(String taskGroup, String taskName) {
        TaskScheduled taskScheduler = new TaskScheduled();
        taskScheduler.setTaskGroup(taskGroup);
        taskScheduler.setTaskName(taskName);
        schedulerManager.runJob(taskScheduler);
    }

    // 暂停/恢复作业
    public void openCloseTask(String taskGroup, String taskName, String status) {
        TaskScheduled taskScheduler = new TaskScheduled();
        taskScheduler.setTaskGroup(taskGroup);
        taskScheduler.setTaskName(taskName);
        if ("start".equals(status)) {
            schedulerManager.resumeJob(taskScheduler);
        } else if ("stop".equals(status)) {
            schedulerManager.stopJob(taskScheduler);
        }
    }

    // 删除作业
    public void delTask(String taskGroup, String taskName) {
        TaskScheduled taskScheduler = new TaskScheduled();
        taskScheduler.setTaskGroup(taskGroup);
        taskScheduler.setTaskName(taskName);
        schedulerManager.delJob(taskScheduler);
    }

    // 修改任务
    public void updateTask(TaskScheduled taskScheduled) {
        schedulerManager.updateTask(taskScheduled);
    }

    @Cacheable("taskFireLog")
    public TaskFireLog getFireLogById(Long id) {
        return logMapper.selectById(id);
    }

    @Transactional
    @CachePut("taskFireLog")
    public TaskFireLog updateLog(TaskFireLog record) {
        if (record.getId() == null) {
            logMapper.insert(record);
        } else {
            logMapper.updateById(record);
        }
        return record;
    }

    public Page<TaskFireLog> queryLog(Map<String, Object> params) {
        Page<Long> ids = BaseProviderImpl.getPage(params);
        ids.setRecords(logMapper.selectIdByMap(ids, params));
        Page<TaskFireLog> page = new Page<TaskFireLog>(ids.getCurrent(), ids.getSize());
        page.setTotal(ids.getTotal());
        if (ids != null) {
            List<TaskFireLog> records = InstanceUtil.newArrayList();
            for (Long id : ids.getRecords()) {
                records.add(InstanceUtil.getBean(getClass()).getFireLogById(id));
            }
            page.setRecords(records);
        }
        return page;
    }
}
