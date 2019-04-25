package cn.comtom.app.standard.component.quartz.config;

import cn.comtom.app.standard.component.quartz.utils.ScheduleTask;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 定时任务配置
 * @author huhailang
 */
@Configuration
public class ScheduleConfig {

    /**
     * 任务获取周期
     */
    public static final Integer IDLE_WAIT_MILLS = 30000;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier(QuartzDataSourceConfig.QUARTZ_DATA_SOURCE) DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);

        //quartz参数
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "ComtomScheduler");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");

        prop.put("org.quartz.scheduler.idleWaitTime", IDLE_WAIT_MILLS);

        //线程池配置
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "20");
        prop.put("org.quartz.threadPool.threadPriority", "5");

        //JobStore配置
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");

        //集群配置
        prop.put("org.quartz.jobStore.isClustered", "true");
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        prop.put("org.quartz.jobStore.txIsolationLevelReadCommitted", " true");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "5");
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        factory.setQuartzProperties(prop);

        factory.setSchedulerName("ComtomScheduler");
        factory.setStartupDelay(15);
        factory.setApplicationContextSchedulerContextKey(ScheduleTask.APPLICATION_CONTEXT_KEY);
        factory.setOverwriteExistingJobs(true);
        //设置自动启动，默认为true
        factory.setAutoStartup(true);

        return factory;
    }
}
