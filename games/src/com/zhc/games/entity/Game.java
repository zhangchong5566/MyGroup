package com.zhc.games.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

@Entity
@Table(name = "Game")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class Game extends AbstractEntityGenerate{
	
	private String gname;//游戏名称
	
	private String description;
	
	private Date createDate;

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
