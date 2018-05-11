package cn.comtom.app.standard.component.quartz.service.impl;

import cn.comtom.app.standard.component.quartz.model.dbo.ScheduleJobLog;
import cn.comtom.app.standard.component.quartz.service.ScheduleJobLogService;
import cn.comtom.app.standard.component.quartz.dao.ScheduleJobLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 定时任务日志实现
 * @author huhailang
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {

	@Autowired
	private ScheduleJobLogDao scheduleJobLogDao;

	@Override
	public ScheduleJobLog queryObject(Long jobId) {
		ScheduleJobLog entity = new ScheduleJobLog();
		entity.setJobId(jobId);
		return scheduleJobLogDao.info(entity);
	}

	@Override
	public List<ScheduleJobLog> queryList(ScheduleJobLog entity) {
		return scheduleJobLogDao.list(entity);
	}

	@Override
	public int queryTotal(ScheduleJobLog entity) {
		return scheduleJobLogDao.count(entity);
	}

	@Override
	public void save(ScheduleJobLog log) {
		scheduleJobLogDao.save(log);
	}

}
