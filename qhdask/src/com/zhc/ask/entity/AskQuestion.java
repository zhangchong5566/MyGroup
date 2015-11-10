package com.zhc.ask.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

/**
 * 问题
* @ClassName: AskQuestion 
* @Description: TODO
* @author zhangchong
* @date 2015年11月9日 下午9:19:01 
*
 */
@Entity
@Table(name = "ask_question")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class AskQuestion extends AbstractEntityGenerate implements Serializable{
	
	private AskMember member;
	
	private AskClassify classify;
	
	private Integer status;//0-未解决，1-已解决
	
	private Integer hidden;//是否隐藏，0-显示，1-隐藏
	
	private String title;
	
	private String description;
	
	private String askTag;//标签

	public AskMember getMember() {
		return member;
	}

	public void setMember(AskMember member) {
		this.member = member;
	}

	public AskClassify getClassify() {
		return classify;
	}

	public void setClassify(AskClassify classify) {
		this.classify = classify;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(columnDefinition="TEXT")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAskTag() {
		return askTag;
	}

	public void setAskTag(String askTag) {
		this.askTag = askTag;
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
	
	

}
