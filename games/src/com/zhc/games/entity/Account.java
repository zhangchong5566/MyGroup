package com.zhc.games.entity;

import static javax.persistence.AccessType.PROPERTY;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

@Entity
@Table(name = "Account")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class Account extends AbstractEntityGenerate{
	
	private Game game;
	
	private String userTag="1";//用户标识，用于区分是哪个用户
	
	private Integer currIndex;//当前第几个账号
	
	private String remark;//备注

	@ManyToOne
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getUserTag() {
		return userTag;
	}

	public void setUserTag(String userTag) {
		this.userTag = userTag;
	}

	public Integer getCurrIndex() {
		return currIndex;
	}

	public void setCurrIndex(Integer currIndex) {
		this.currIndex = currIndex;
	}

	@Column(columnDefinition="TEXT")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
