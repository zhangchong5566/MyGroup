package com.zhc.schedule.quartz.service;

import java.util.List;
import java.util.Map;

import com.zhc.schedule.quartz.entity.QzJobDetail;
import com.zhc.schedule.quartz.entity.QzTrigger;
import com.zhc.sys.service.base.BaseJpaServiceInterf;
import com.zhc.sys.service.base.Pages;

public interface ScheduleService extends BaseJpaServiceInterf{
	
	public static final String ID_NAME = "scheduleService";
	
	public List<QzTrigger> getNormalQzTriggers();
	
	public QzTrigger getQzTrigger(long id);
	
	/**
	 * 获取虚拟任务的
	* @Title: getVirtualTriggers 
	* @Description: TODO
	* @param @return
	* @return List<QzTrigger>
	* @throws 
	* @date 2014年11月17日 下午2:41:46
	 */
	public List<Map> getVirtualTriggers(Pages pages);
	
	
	public List<QzJobDetail> listJob(Pages pages);
	
	public List<QzTrigger> listTrigger(Pages pages);
	

}
