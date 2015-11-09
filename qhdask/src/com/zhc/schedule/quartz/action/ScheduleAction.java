package com.zhc.schedule.quartz.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;

import com.zhc.schedule.quartz.ScheduleManager;
import com.zhc.schedule.quartz.entity.QzJobDetail;
import com.zhc.schedule.quartz.entity.QzTrigger;
import com.zhc.schedule.quartz.service.ScheduleService;
import com.zhc.sys.action.BaseAction;
import com.zhc.util.DateUtil;

@Controller
@ParentPackage("json-default")
public class ScheduleAction extends BaseAction {

	@Resource(name = ScheduleService.ID_NAME)
	private ScheduleService scheduleService;

	@Resource(name = ScheduleManager.ID_NAME)
	private ScheduleManager scheduleManager;

	private List<QzJobDetail> jobList;

	private List<QzTrigger> triggerList;

	private QzJobDetail job;

	private QzTrigger trigger;

	private String result;

	@Action(value = "/manage/quartz/toJob", results = { @Result(name = SUCCESS, location = "/manage/quartz/job_list.jsp") })
	public String toJob() {

		return SUCCESS;
	}

	@Action(value = "/manage/quartz/listJob", results = { @Result(name = SUCCESS, type = "json", params = {
			"ignoreHierarchy", "false" }) })
	public String listJob() {

		jobList = scheduleService.listJob(super.getReqPages());

		return SUCCESS;
	}

	@Action(value = "/manage/quartz/toEditJob", results = { @Result(name = SUCCESS, location = "/manage/quartz/job_edit.jsp") })
	public String toEditJob() {

		long id = super.getLongParamter("id", 0);

		if (id > 0) {
			job = scheduleService.find(QzJobDetail.class, id);
		}

		return SUCCESS;
	}

	@Action(value = "/manage/quartz/saveJob", results = { @Result(name = SUCCESS, type = "json", params = {
			"ignoreHierarchy", "false" }) })
	public String saveJob() {

		if (job != null) {

			if (job.getId() != null && job.getId() > 0) {
				scheduleService.update(job);
			} else {
				scheduleService.create(job);
			}
		}
		result = "Success";

		return SUCCESS;
	}

	@Action(value = "/manage/quartz/toTrigger", results = { @Result(name = SUCCESS, location = "/manage/quartz/trigger_list.jsp") })
	public String toTrigger() {

		return SUCCESS;
	}

	@Action(value = "/manage/quartz/listTrigger", results = { @Result(name = SUCCESS, type = "json", params = {
			"ignoreHierarchy", "false" }) })
	public String listTrigger() {

		triggerList = scheduleService.listTrigger(super.getReqPages());

		return SUCCESS;
	}

	@Action(value = "/manage/quartz/toEditTrigger", results = { @Result(name = SUCCESS, location = "/manage/quartz/trigger_edit.jsp") })
	public String toEditTrigger() {

		long id = super.getLongParamter("id", 0);

		if (id > 0) {
			trigger = scheduleService.find(QzTrigger.class, id);
		}
		jobList = scheduleService.listJob(null);
		return SUCCESS;
	}

	@Action(value = "/manage/quartz/saveTrigger", results = { @Result(name = SUCCESS, type = "json", params = {
			"ignoreHierarchy", "false" }) })
	public String saveTrigger() {

		long qzJobDetailId = super.getLongParamter("qzJobDetailId", 0);
		String startDate = super.getStr("startDate");
		String endDate = super.getStr("endDate");

		if (trigger != null) {
			if (qzJobDetailId > 0) {
				QzJobDetail job = scheduleService.find(QzJobDetail.class,
						qzJobDetailId);
				trigger.setQzJobDetail(job);
			}
			if (StringUtils.isNotBlank(startDate)) {
				try {
					trigger.setStartDate(DateUtil.parseToDate(startDate,
							"yyyy-MM-dd HH:mm:ss"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotBlank(endDate)) {
				try {
					trigger.setEndDate(DateUtil.parseToDate(endDate,
							"yyyy-MM-dd HH:mm:ss"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (trigger.getId() != null && trigger.getId() > 0) {
				scheduleService.update(trigger);
			} else {
				scheduleService.create(trigger);
			}

		}

		result = "Success";

		return SUCCESS;
	}

	@Action(value = "/manage/quartz/restartAll", results = { @Result(name = SUCCESS, type = "json", params = {
			"ignoreHierarchy", "false" }) })
	public String restartAll() {

		try {
			scheduleManager.reStart();
			result = "Success";
		} catch (SchedulerException e) {
			result = "Error:" + e.getMessage();
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加任务
	* @Title: addTrigger 
	* @Description: TODO
	* @param 
	* @return void
	* @throws 
	* @date 2015年3月9日 下午4:07:33
	 */
	@Action(value = "/addTrigger")
	public void addTrigger() {
		try {
			long qzJobDetailId = super.getLongParamter("qzJobDetailId", 0);
			String startDate = super.getStr("startDate");
			String endDate = super.getStr("endDate");

			if (trigger != null) {
				if (qzJobDetailId > 0) {
					QzJobDetail job = scheduleService.find(QzJobDetail.class,
							qzJobDetailId);
					trigger.setQzJobDetail(job);
				}
				if (StringUtils.isNotBlank(startDate)) {
					try {
						trigger.setStartDate(DateUtil.parseToDate(startDate,
								"yyyy-MM-dd HH:mm:ss"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if (StringUtils.isNotBlank(endDate)) {
					try {
						trigger.setEndDate(DateUtil.parseToDate(endDate,
								"yyyy-MM-dd HH:mm:ss"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				scheduleService.create(trigger);
				
				if (trigger.getStatus() == 1) {
					try {
						scheduleManager.scheduleJob(trigger);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				}
			}

			response.getWriter().println("Success");
			
		} catch (IOException e) {
			e.printStackTrace();
			try {
				response.getWriter().println("Error");
			} catch (IOException e1) {
			}
		}
	}

	public List<QzJobDetail> getJobList() {
		return jobList;
	}

	public void setJobList(List<QzJobDetail> jobList) {
		this.jobList = jobList;
	}

	public QzJobDetail getJob() {
		return job;
	}

	public void setJob(QzJobDetail job) {
		this.job = job;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<QzTrigger> getTriggerList() {
		return triggerList;
	}

	public void setTriggerList(List<QzTrigger> triggerList) {
		this.triggerList = triggerList;
	}

	public QzTrigger getTrigger() {
		return trigger;
	}

	public void setTrigger(QzTrigger trigger) {
		this.trigger = trigger;
	}

}
