package cn.comtom.app.standard.component.quartz.utils;

import cn.comtom.app.standard.component.quartz.model.dbo.ScheduleJob;
import cn.comtom.app.standard.component.quartz.service.ScheduleJobLogService;
import cn.comtom.app.standard.component.quartz.model.dbo.ScheduleJobLog;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 定时任务
 * @author huhailang
 */
public class ScheduleTask extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String jsonJob = context.getMergedJobDataMap().getString(ScheduleJob.JOB_PARAM_KEY);
        ScheduleJob scheduleJob = new Gson().fromJson(jsonJob, ScheduleJob.class);

        //获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");

        //数据库保存执行记录
        ScheduleJobLog log = new ScheduleJobLog();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
                    scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);

            future.get();

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int) times);

            //任务状态    0：成功    1：失败
            log.setStatus(0);

            logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int) times);

            //任务状态    0：成功    1：失败
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            // 屏蔽日志记录数据库
            scheduleJobLogService.save(log);
        }
    }
}
