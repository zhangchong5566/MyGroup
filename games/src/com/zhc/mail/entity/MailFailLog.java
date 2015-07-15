package com.zhc.mail.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

@Entity
@Table(name = "MailFailLog")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class MailFailLog extends AbstractEntityGenerate{
	
	
	private String receiverMails;//接收的邮箱地址，多个地址使用[,]分隔
	
	private String title;
	
	private String content;
	
	private String exception;
	
	private Date sendDate;
	
	private Date execDate;//处理时间
	
	private Integer status=0;//0-未处理，1-已处理


	@Column(columnDefinition = "TEXT")
	public String getReceiverMails() {
		return receiverMails;
	}

	public void setReceiverMails(String receiverMails) {
		this.receiverMails = receiverMails;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(columnDefinition = "TEXT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getExecDate() {
		return execDate;
	}

	public void setExecDate(Date execDate) {
		this.execDate = execDate;
	}

	@Column(columnDefinition = "TEXT")
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
	

}
