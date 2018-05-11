package cn.comtom.app.standard.component.quartz.dao;

import cn.comtom.app.standard.component.quartz.mapper.ScheduleJobMapper;
import cn.comtom.app.standard.component.quartz.model.dbo.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 定时任务
 * @author huhailang
 */
@Repository
public class ScheduleJobDao {

	@Autowired
	private ScheduleJobMapper scheduleJobMapper;

	public List<ScheduleJob> listAll(){
		return scheduleJobMapper.select(new ScheduleJob());
	}

	public ScheduleJob info(Long id){
		ScheduleJob entity = new ScheduleJob();
		entity.setJobId(id);
		return scheduleJobMapper.selectOne(entity);
	}

	public List<ScheduleJob> list(ScheduleJob entity){
		return scheduleJobMapper.select(entity);

	}

	public int queryTotal(ScheduleJob entity){
		return scheduleJobMapper.selectCount(entity);
	}

	public ScheduleJob save(ScheduleJob entity){
		 scheduleJobMapper.insert(entity);
		return entity;
	}

	public void update(ScheduleJob entity){

		Example example = new Example(ScheduleJob.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("jobId",entity.getJobId());
		entity.setJobId(null);
		scheduleJobMapper.updateByExampleSelective(entity,example);
	}

	public void delete(ScheduleJob entity){
		scheduleJobMapper.delete(entity);
	}

}
