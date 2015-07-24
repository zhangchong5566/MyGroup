package com.zhc.schedule.quartz;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.zhc.schedule.quartz.entity.QzTrigger;
import com.zhc.schedule.quartz.service.ScheduleService;

/**
 * 定时任务调度，启动即加载
* @ClassName: ScheduleManager 
* @Description: TODO
* @author zhangchong
* @date 2014年11月6日 上午9:40:56 
*
 */
public class ScheduleManager implements InitializingBean, DisposableBean {
	private Scheduler scheduler;
	private ScheduleService scheduleService;
	private static final String DEFAULT_GROUP = "Shapetizer";
	public static final String ID_NAME = "scheduleManager";
	public static final String HEADER_TRI = "tri_";
	public static final String HEADER_JOB = "job_";
	protected static final Log logger = LogFactory
			.getLog(ScheduleManager.class);
	public List<String> triggerNames = new ArrayList();

	public void scheduleJob(QzTrigger qzTrigger) throws ClassNotFoundException,
			ParseException, SchedulerException {
		Trigger trigger = null;
		TriggerBuilder<Trigger> tb = TriggerBuilder.newTrigger().withIdentity("tri_" + qzTrigger.getId(), "Shapetizer");;
		
		JobKey key = new JobKey("job_"
				+ qzTrigger.getQzJobDetail().getId() + "tri_"
				+ qzTrigger.getId(), "Shapetizer");
		
		JobDetail jobDetail = null;
		try {
			jobDetail = JobBuilder.newJob(((Job)Class.forName(qzTrigger.getQzJobDetail().getClassName()).newInstance()).getClass()).withIdentity(key).build();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		if (qzTrigger.getQzJobDetail().getParameters() != null) {
			String[] parameters = qzTrigger.getQzJobDetail().getParameters()
					.split(";");
			for (String parameter : parameters) {
				if (!"".equals(parameter)) {
					String[] param = parameter.split("=");
					jobDetail.getJobDataMap().put(param[0], param[1]);
				}
			}
		}
		jobDetail.getJobDataMap().put("triId", qzTrigger.getId());
		jobDetail.getJobDataMap().put("triName", qzTrigger.getName());
		jobDetail.getJobDataMap().put("triDescription",
				qzTrigger.getDescription());
		if (qzTrigger.getQzJobDetail().getExtendf1() != null) {
			jobDetail.getJobDataMap().put("jobArg1",
					qzTrigger.getQzJobDetail().getExtendf1());
		}
		if (qzTrigger.getQzJobDetail().getExtendf2() != null) {
			jobDetail.getJobDataMap().put("jobArg2",
					qzTrigger.getQzJobDetail().getExtendf2());
		}
		if (qzTrigger.getQzJobDetail().getExtendf3() != null) {
			jobDetail.getJobDataMap().put("jobArg3",
					qzTrigger.getQzJobDetail().getExtendf3());
		}
		if (qzTrigger.getQzJobDetail().getExtendf4() != null) {
			jobDetail.getJobDataMap().put("jobArg4",
					qzTrigger.getQzJobDetail().getExtendf4());
		}
		if (qzTrigger.getQzJobDetail().getExtendf5() != null) {
			jobDetail.getJobDataMap().put("jobArg5",
					qzTrigger.getQzJobDetail().getExtendf5());
		}
		if (qzTrigger.getQzJobDetail().getExtendf6() != null) {
			jobDetail.getJobDataMap().put("jobArg6",
					qzTrigger.getQzJobDetail().getExtendf6());
		}
		if (qzTrigger.getQzJobDetail().getExtendf7() != null) {
			jobDetail.getJobDataMap().put("jobArg7",
					qzTrigger.getQzJobDetail().getExtendf7());
		}
		if (qzTrigger.getQzJobDetail().getExtendf8() != null) {
			jobDetail.getJobDataMap().put("jobArg8",
					qzTrigger.getQzJobDetail().getExtendf8());
		}
		
		//设置开始，结束时间
		if (qzTrigger.getStartDate() != null) {
			tb.startAt(qzTrigger.getStartDate());
		} else if (qzTrigger.getStartDelay() != null) {
			tb.startAt(new Date(System
					.currentTimeMillis()
					+ qzTrigger.getStartDelay().intValue()));
		}
		if (qzTrigger.getEndDate() != null) {
			tb.endAt(qzTrigger.getEndDate());
		}
		
		if (qzTrigger.getType().intValue() == 0) {
			//循环执行
			SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
			
			if (qzTrigger.getRepeatCount() != null) {
				scheduleBuilder.withRepeatCount(qzTrigger
						.getRepeatCount().intValue());
				
			}
			if ((qzTrigger.getRepeatInterval() != null)
					&& (qzTrigger.getRepeatInterval().intValue() > 0)) {
				scheduleBuilder.withIntervalInMilliseconds(qzTrigger
						.getRepeatInterval().intValue());
			}
			tb.withSchedule(scheduleBuilder);
			
		} else {
			//精确定时
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(qzTrigger.getCronExpression());
			tb.withSchedule(scheduleBuilder);
		}
		
		if (qzTrigger.getExtendf1() != null) {
			jobDetail.getJobDataMap().put("triArg1", qzTrigger.getExtendf1());
		}
		if (qzTrigger.getExtendf2() != null) {
			jobDetail.getJobDataMap().put("triArg2", qzTrigger.getExtendf2());
		}
		if (qzTrigger.getExtendf3() != null) {
			jobDetail.getJobDataMap().put("triArg3", qzTrigger.getExtendf3());
		}
		if (qzTrigger.getExtendf4() != null) {
			jobDetail.getJobDataMap().put("triArg4", qzTrigger.getExtendf4());
		}
		if (qzTrigger.getExtendf5() != null) {
			jobDetail.getJobDataMap().put("triArg5", qzTrigger.getExtendf5());
		}
		if (qzTrigger.getExtendf6() != null) {
			jobDetail.getJobDataMap().put("triArg6", qzTrigger.getExtendf6());
		}
		if (qzTrigger.getExtendf7() != null) {
			jobDetail.getJobDataMap().put("triArg7", qzTrigger.getExtendf7());
		}
		if (qzTrigger.getExtendf8() != null) {
			jobDetail.getJobDataMap().put("triArg8", qzTrigger.getExtendf8());
		}
		if (qzTrigger.getExtendf9() != null) {
			jobDetail.getJobDataMap().put("triArg9", qzTrigger.getExtendf9());
		}

		trigger = tb.withIdentity( "tri_"+ qzTrigger.getId(), "Shapetizer").build();
		this.scheduler.scheduleJob(jobDetail, trigger);
		this.triggerNames.add(trigger.getKey().getName());
	}

	public void initDb() {
		this.triggerNames.clear();
		try {
			logger.info("任务调度器开始装载数据库任务信息....");
			List<QzTrigger> qzTriggers = this.scheduleService
					.getNormalQzTriggers();
			QzTrigger qzTrigger = null;
			for (int i = 0; i < qzTriggers.size(); i++) {
				qzTrigger = (QzTrigger) qzTriggers.get(i);
				try {
					scheduleJob(qzTrigger);
				} catch (Exception e) {
					logger.error("initDb", e);
				}
			}
		} catch (Exception e) {
			logger.error("initDb", e);
		}
	}

	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	public void afterPropertiesSet() throws Exception {
		initDb();
	}

	public void destroy() throws Exception {
		if (!this.scheduler.isShutdown()) {
			this.scheduler.shutdown();
		}
	}

	public void pauseAllJob() {
		try {
			this.scheduler.pauseAll();
		} catch (SchedulerException e) {
			logger.error("pauseAllJob", e);
		}
	}

	public void pauseTrigger(long tid) {
		try {
			this.scheduler.pauseTrigger(new TriggerKey("tri_" + tid, DEFAULT_GROUP));
		} catch (SchedulerException e) {
			logger.error("pauseTrigger", e);
		}
	}

	public void unscheduleJob(long tid) {
		try {
			if (this.triggerNames.indexOf("tri_" + tid) != -1) {
				this.scheduler.unscheduleJob(new TriggerKey("tri_" + tid, DEFAULT_GROUP));
			}
		} catch (SchedulerException e) {
			logger.error("unscheduleJob", e);
		}
	}

	public void scheduleJob(long id) throws ClassNotFoundException,
			ParseException, SchedulerException {
		QzTrigger qzTrigger = this.scheduleService.getQzTrigger(id);
		scheduleJob(qzTrigger);
	}

	public void reStart() throws SchedulerException {
		for (String triggerName : this.triggerNames) {
			this.scheduler.unscheduleJob(new TriggerKey(triggerName, DEFAULT_GROUP));
		}
		initDb();
	}
	
	/**
	 * 更改状态为禁用并取消执行
	* @Title: disableJob 
	* @Description: TODO
	* @param @param tid
	* @return void
	* @throws 
	* @date 2015年3月11日 上午11:05:00
	 */
	public void disableJob(long tid){
		unscheduleJob(tid);
		QzTrigger qzTrigger = this.scheduleService.getQzTrigger(tid);
		qzTrigger.setEndDate(new Date());
		qzTrigger.setStatus(0);
		scheduleService.update(qzTrigger);
	}
	
	public void addAndEnableJob(QzTrigger trigger){
		trigger.setStatus(1);
		scheduleService.create(trigger);
		try {
			scheduleJob(trigger);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public ScheduleService getScheduleService() {
		return this.scheduleService;
	}

	public Scheduler getScheduler() {
		return this.scheduler;
	}

	
	
}
