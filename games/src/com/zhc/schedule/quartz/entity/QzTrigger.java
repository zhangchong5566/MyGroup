package com.zhc.schedule.quartz.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

@Entity
@Table(name = "qz_trigger")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class QzTrigger extends AbstractEntityGenerate implements Serializable {

	private QzJobDetail qzJobDetail;
	private String name;
	private String description;
	private Integer type;// 0:循环执行，1-精确定时
	private Integer startDelay;//启动后多久开始执行
	private Integer repeatCount;//执行次数
	private Integer repeatInterval;//循环执行，每次执行的间隔时间
	private String cronExpression;//精确执行的时间表达式
	private Date startDate;
	private Date endDate;
	private Long extendf1;
	private Long extendf2;
	private Integer extendf3;//virtual :1=follow,2=like,3=comment,4=thank
	private Integer extendf4;
	private String extendf5;
	private String extendf6;
	private String extendf7;
	private String extendf8;
	private String extendf9;
	private Integer status=1;// 1:启用，其他：禁用

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getExtendf1() {
		return this.extendf1;
	}

	public void setExtendf1(Long extendf1) {
		this.extendf1 = extendf1;
	}

	public Long getExtendf2() {
		return this.extendf2;
	}

	public void setExtendf2(Long extendf2) {
		this.extendf2 = extendf2;
	}

	public Integer getExtendf3() {
		return this.extendf3;
	}

	public void setExtendf3(Integer extendf3) {
		this.extendf3 = extendf3;
	}

	public Integer getExtendf4() {
		return this.extendf4;
	}

	public void setExtendf4(Integer extendf4) {
		this.extendf4 = extendf4;
	}

	public String getExtendf5() {
		return this.extendf5;
	}

	public void setExtendf5(String extendf5) {
		this.extendf5 = extendf5;
	}

	public String getExtendf6() {
		return this.extendf6;
	}

	public void setExtendf6(String extendf6) {
		this.extendf6 = extendf6;
	}

	public String getExtendf7() {
		return this.extendf7;
	}

	public void setExtendf7(String extendf7) {
		this.extendf7 = extendf7;
	}

	public String getExtendf8() {
		return this.extendf8;
	}

	public void setExtendf8(String extendf8) {
		this.extendf8 = extendf8;
	}

	@ManyToOne
	public QzJobDetail getQzJobDetail() {
		return this.qzJobDetail;
	}

	public void setQzJobDetail(QzJobDetail qzJobDetail) {
		this.qzJobDetail = qzJobDetail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStartDelay() {
		return this.startDelay;
	}

	public void setStartDelay(Integer startDelay) {
		this.startDelay = startDelay;
	}

	public Integer getRepeatCount() {
		return this.repeatCount;
	}

	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	public Integer getRepeatInterval() {
		return this.repeatInterval;
	}

	public void setRepeatInterval(Integer repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public String getCronExpression() {
		return this.cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(columnDefinition="text")
	public String getExtendf9() {
		return extendf9;
	}

	public void setExtendf9(String extendf9) {
		this.extendf9 = extendf9;
	}

}
