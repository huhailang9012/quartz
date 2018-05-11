package cn.comtom.app.standard.component.quartz.service;

import cn.comtom.app.standard.component.quartz.model.dbo.ScheduleJob;

import java.util.List;

/**
 * 定时任务
 * @author huhailang
 */
public interface ScheduleJobService {

	/**
	 * 根据ID，查询定时任务
	 */
	ScheduleJob queryObject(Long jobId);
	
	/**
	 * 查询定时任务列表
	 */
	List<ScheduleJob> queryList(ScheduleJob scheduleJob);
	
	/**
	 * 查询总数
	 */
	int queryTotal(ScheduleJob scheduleJob);
	
	/**
	 * 保存定时任务
	 */
	void save(ScheduleJob scheduleJob);
	
	/**
	 * 更新定时任务
	 */
	void update(ScheduleJob scheduleJob);
	
	/**
	 * 立即执行
	 */
	void run(Long jobId);
	
	/**
	 * 暂停运行
	 */
	void pause(Long jobId);
	
	/**
	 * 恢复运行
	 */
	void resume(Long jobId);

	/**
	 * 恢复运行
	 */
	void delete(Long jobId);
}
