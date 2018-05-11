package cn.comtom.app.standard.component.quartz.model.dbo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时执行日志
 * @author huhailang
 */
@Table(name = "schedule_job_log")
public class ScheduleJobLog implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志id
	 */
	@Id
	@Column(name = "log_id")
	@GeneratedValue(generator = "JDBC")
	private Long logId;
	
	/**
	 * 任务id
	 */
	private Long jobId;
	
	/**
	 * spring bean名称
	 */
	private String beanName;
	
	/**
	 * 方法名
	 */
	private String methodName;
	
	/**
	 * 参数
	 */
	private String params;
	
	/**
	 * 任务状态    0：成功    1：失败
	 */
	private Integer status;
	
	/**
	 * 失败信息
	 */
	private String error;
	
	/**
	 * 耗时(单位：毫秒)
	 */
	private Integer times;

	/**
	 * 业务ID
	 */
	private String serviceId;

	/**
	 * 业务类型
	 */
	private Integer serviceType;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
}
