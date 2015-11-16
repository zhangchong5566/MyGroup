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
	
	private Integer status;//1-未解决，2-已解决
	
	private Integer hidden;//是否隐藏，1-显示，2-隐藏
	
	private Integer isopen;//是否公开，1-公开，2-不公开，不公开只有专家登录以后并且是审核同构的专家才可以看到
	
	private Long viewCount;//查看数量 
	
	private Integer replayCount=0;//回答数量
	
	private Date createDate;//提问时间
	
	private Date lastReplayDate;//最后回复时间
	
	private String title;
	
	private String description;
	
	private String askTag;//标签

	@ManyToOne
	public AskMember getMember() {
		return member;
	}

	public void setMember(AskMember member) {
		this.member = member;
	}
	@ManyToOne
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

	public Integer getReplayCount() {
		return replayCount;
	}

	public void setReplayCount(Integer replayCount) {
		this.replayCount = replayCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastReplayDate() {
		return lastReplayDate;
	}

	public void setLastReplayDate(Date lastReplayDate) {
		this.lastReplayDate = lastReplayDate;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getIsopen() {
		return isopen;
	}

	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}

	
	

}
