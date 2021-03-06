package com.zhc.ask.entity;

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

/**
 * 回答
* @ClassName: AskAnswer 
* @Description: TODO
* @author zhangchong
* @date 2015年11月9日 下午9:23:38 
*
 */
@Entity
@Table(name = "ask_answer")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class AskAnswer extends AbstractEntityGenerate implements Serializable{

	private AskMember member;//回答者
	
	private AskQuestion question;
	
	private String answerContent;//回答内容
	
	private Integer status;//1-未采纳，2-已采纳
	
	private Integer hidden;//是否隐藏，1-显示，2-隐藏
	
	private Date createDate;

	@ManyToOne
	public AskMember getMember() {
		return member;
	}

	public void setMember(AskMember member) {
		this.member = member;
	}
	
	
	@ManyToOne
	public AskQuestion getQuestion() {
		return question;
	}

	public void setQuestion(AskQuestion question) {
		this.question = question;
	}

	@Column(columnDefinition="TEXT")
	public String getAnswerContent() {
		return answerContent;
	}
	
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getHidden() {
		return hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
