package com.lishao.system.core.support.scheduler;

import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lishao.system.core.Constants.JOBSTATE;
import com.lishao.system.core.support.email.Email;
import com.lishao.system.core.support.mq.QueueSender;
import com.lishao.system.core.util.NativeUtil;
import com.lishao.system.model.scheduler.TaskFireLog;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

/**
 * 调度监听器
 * 
 * @author ShenHuaJie
 * @version 2016年5月27日 下午4:31:31
 */
public class JobListener implements org.quartz.JobListener {
    private Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private SchedulerService schedulerService;
    @Autowired
    private QueueSender queueSender;
    // 线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private String JOB_LOG = "jobLog";

    public String getName() {
        return "taskListener";
    }

    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    // 任务开始前
    public void jobToBeExecuted(final JobExecutionContext context) {
        final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String targetObject = jobDataMap.getString("targetObject");
        String targetMethod = jobDataMap.getString("targetMethod");
        if (logger.isInfoEnabled()) {
            logger.info("定时任务开始执行：{}.{}", targetObject, targetMethod);
        }
        // 保存日志
        TaskFireLog log = new TaskFireLog();
        log.setStartTime(context.getFireTime());
        log.setGroupName(targetObject);
        log.setTaskName(targetMethod);
        log.setStatus(JOBSTATE.INIT_STATS);
        log.setServerHost(NativeUtil.getHostName());
        log.setServerDuid(NativeUtil.getDUID());
        schedulerService.updateLog(log);
        jobDataMap.put(JOB_LOG, log);
    }

    // 任务结束后
    public void jobWasExecuted(final JobExecutionContext context, JobExecutionException exp) {
        Timestamp end = new Timestamp(System.currentTimeMillis());
        final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String targetObject = jobDataMap.getString("targetObject");
        String targetMethod = jobDataMap.getString("targetMethod");
        if (logger.isInfoEnabled()) {
            logger.info("定时任务执行结束：{}.{}", targetObject, targetMethod);
        }
        // 更新任务执行状态
        final TaskFireLog log = (TaskFireLog)jobDataMap.get(JOB_LOG);
        if (log != null) {
            log.setEndTime(end);
            if (exp != null) {
                String contactEmail = jobDataMap.getString("contactEmail");
                if (StringUtils.isNotBlank(contactEmail)) {
                    String topic = String.format("调度[%s.%s]发生异常", targetMethod, targetMethod);
                    sendEmail(new Email(contactEmail, topic, exp.getMessage()));
                }
                log.setStatus(JOBSTATE.ERROR_STATS);
                log.setFireInfo(exp.getMessage());
            } else {
                if (log.getStatus().equals(JOBSTATE.INIT_STATS)) {
                    log.setStatus(JOBSTATE.SUCCESS_STATS);
                }
            }
        }
        executorService.submit(new Runnable() {
            public void run() {
                if (log != null) {
                    try {
                        schedulerService.updateLog(log);
                    } catch (Exception e) {
                        logger.error("Update TaskRunLog cause error. The log object is : " + JSON.toJSONString(log), e);
                    }
                }
            }
        });
    }

    private void sendEmail(final Email email) {
        executorService.submit(new Runnable() {
            public void run() {
                queueSender.send("iBase4J.emailSender", email);
            }
        });
    }
}
