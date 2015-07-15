package com.zhc.schedule.quartz.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;


@Entity
@Table(name = "qz_log")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class QzLog extends AbstractEntityGenerate{
	
	private String jobName;//任务名称
	
	private Date execTime;//执行时间
	
	private Integer result;//执行结果，1-失败，0-成功
	
	private String exception;//异常信息
	
	private String otherRemark;//其他描述

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Date getExecTime() {
		return execTime;
	}

	public void setExecTime(Date execTime) {
		this.execTime = execTime;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@Column(columnDefinition = "TEXT")
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@Column(columnDefinition = "TEXT")
	public String getOtherRemark() {
		return otherRemark;
	}

	public void setOtherRemark(String otherRemark) {
		this.otherRemark = otherRemark;
	}
	
	

}
