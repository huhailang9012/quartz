package cn.comtom.app.standard.component.quartz.dao;

import cn.comtom.app.standard.component.quartz.mapper.ScheduleJobLogMapper;
import cn.comtom.app.standard.component.quartz.model.dbo.ScheduleJobLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定时任务日志
 * @author: huhailang
 */
@Repository
public class ScheduleJobLogDao {

    @Autowired
    private ScheduleJobLogMapper scheduleJobLogMapper;

    public void save(ScheduleJobLog entity){
        scheduleJobLogMapper.insert(entity);
    }

    public int count(ScheduleJobLog entity){
        return scheduleJobLogMapper.selectCount(entity);
    }

    public List<ScheduleJobLog> list(ScheduleJobLog entity){
        return scheduleJobLogMapper.select(entity);
    }

    public ScheduleJobLog info(ScheduleJobLog entity){
        return scheduleJobLogMapper.selectOne(entity);
    }

}
