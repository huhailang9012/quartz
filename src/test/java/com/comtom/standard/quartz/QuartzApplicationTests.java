//package com.comtom.standard.quartz;
//
//import cn.comtom.app.standard.component.quartz.model.Constant;
//import cn.comtom.app.standard.component.quartz.model.dbo.ScheduleJob;
//import cn.comtom.app.standard.component.quartz.service.ScheduleJobService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = QuartzApplication.class)
//public class QuartzApplicationTests {
//
//	@Autowired
//    ScheduleJobService scheduleJobService;
//
//	@Test
//	public void testSave(){
//		ScheduleJob entity = new ScheduleJob();
//		entity.setCreateTime(new Date());
//		entity.setBeanName("SchemaFreezeJob");
//		entity.setMethodName("freeze");
//        entity.setParams("{\"cloudTaskIds\":[\"486277ac31be447599ad08e55ec7d7d1\",\"56a1d114ecbd4188aa2ad06ed195649b\"],\"orgId\":10,\"taskIds\":[24,47]}");
//		entity.setRemark("方案冻结");
//		entity.setCronExpression("00 30 18 27 04 ? 2018");
//		entity.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
//		scheduleJobService.save(entity);
//	}
//
//	@Test
//	public void testDelete(){
//		scheduleJobService.delete(22l);
//	}
//
//	@Test
//	public void testUpdate(){
//		ScheduleJob entity = new ScheduleJob();
//		entity.setJobId(22l);
//		entity.setCreateTime(new Date());
//		entity.setBeanName("SchemaFreezeJob");
//		entity.setMethodName("freeze");
//		entity.setParams("{\"cloudTaskIds\":[\"486277ac31be447599ad08e55ec7d7d1\",\"56a1d114ecbd4188aa2ad06ed195649b\"],\"orgId\":10,\"taskIds\":[24,47]}");
//		entity.setCronExpression("00 35 17 27 04 ? 2018");
//		entity.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
//		scheduleJobService.update(entity);
//	}
//
//}
