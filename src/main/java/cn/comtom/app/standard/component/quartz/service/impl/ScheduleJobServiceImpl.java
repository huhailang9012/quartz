package cn.comtom.app.standard.component.quartz.service.impl;

import cn.comtom.app.standard.component.quartz.config.QuartzDataSourceConfig;
import cn.comtom.app.standard.component.quartz.dao.ScheduleJobDao;
import cn.comtom.app.standard.component.quartz.model.Constant;
import cn.comtom.app.standard.component.quartz.model.dbo.ScheduleJob;
import cn.comtom.app.standard.component.quartz.service.ScheduleJobService;
import cn.comtom.app.standard.component.quartz.utils.ScheduleUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
/**
 * 定时任务实现
 * @author huhailang
 */
@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {

	private static final String TRANSATION_MANAGER = QuartzDataSourceConfig.QUARTZ_TRANSACTION_MANAGER;

	private final Scheduler scheduler;

	private final ScheduleJobDao scheduleJobDao;

	@Autowired
	public ScheduleJobServiceImpl(Scheduler scheduler, ScheduleJobDao scheduleJobDao) {
		this.scheduler = scheduler;
		this.scheduleJobDao = scheduleJobDao;
	}

//	/**
//	 * 项目启动时，初始化定时器
//	 */
//	//@PostConstruct
//	public void init(){
//		List<ScheduleJob> scheduleJobList = scheduleJobDao.listAll();
//		for(ScheduleJob scheduleJob : scheduleJobList){
//			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
//            //如果不存在，则创建
//            if(cronTrigger == null) {
//                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
//            }else {
//                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
//            }
//		}
//	}
	
	@Override
	public ScheduleJob queryObject(Long jobId) {
		return scheduleJobDao.info(jobId);
	}

	@Override
	public List<ScheduleJob> queryList(ScheduleJob entity) {
		return scheduleJobDao.list(entity);
	}

	@Override
	public int queryTotal(ScheduleJob entity) {
		return scheduleJobDao.queryTotal(entity);
	}

	@Override
	@Transactional(TRANSATION_MANAGER)
	public void save(ScheduleJob scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
		scheduleJob = scheduleJobDao.save(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }
	
	@Override
	@Transactional(TRANSATION_MANAGER)
	public void update(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
		scheduleJobDao.update(scheduleJob);
    }


	@Override
	@Transactional(TRANSATION_MANAGER)
    public void run(Long jobId) {
		ScheduleUtils.run(scheduler, queryObject(jobId));
    }

	@Override
	@Transactional(TRANSATION_MANAGER)
    public void pause(Long jobId) {
		ScheduleUtils.pauseJob(scheduler, jobId);
		ScheduleJob entity = new ScheduleJob();
		entity.setJobId(jobId);
		entity.setStatus(Constant.ScheduleStatus.PAUSE.getValue());
		scheduleJobDao.update(entity);
    }

	@Override
	@Transactional(TRANSATION_MANAGER)
    public void resume(Long jobId) {

		ScheduleUtils.resumeJob(scheduler, jobId);
		ScheduleJob entity = new ScheduleJob();
		entity.setJobId(jobId);
		entity.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
		scheduleJobDao.update(entity);
    }

	@Override
	@Transactional(TRANSATION_MANAGER)
	public void delete(Long jobId) {
		ScheduleUtils.deleteScheduleJob(scheduler,jobId);
		ScheduleJob entity = new ScheduleJob();
		entity.setJobId(jobId);
		scheduleJobDao.delete(entity);
	}

}
