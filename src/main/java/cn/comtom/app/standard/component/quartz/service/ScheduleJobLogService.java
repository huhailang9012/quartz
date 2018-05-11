package cn.comtom.app.standard.component.quartz.service;

import cn.comtom.app.standard.component.quartz.model.dbo.ScheduleJobLog;

import java.util.List;

/**
 * 定时任务日志
 * @author huhailang
 */
public interface ScheduleJobLogService {

	/**
	 * 根据ID，查询定时任务日志
	 */
	ScheduleJobLog queryObject(Long jobId);
	
	/**
	 * 查询定时任务日志列表
	 */
	List<ScheduleJobLog> queryList(ScheduleJobLog entity);
	
	/**
	 * 查询总数
	 */
	int queryTotal(ScheduleJobLog entity);
	
	/**
	 * 保存定时任务日志
	 */
	void save(ScheduleJobLog log);
	
}
