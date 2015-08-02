package com.zhc.games.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

@Entity
@Table(name = "SerialNumber")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class SerialNumber extends AbstractEntityGenerate{
	
	private Game game;//所属游戏
	
	private String serialNumber;//手机串号
	
	private String price;//售价
	
	private Integer type;//授权的脚本类型，0-All，1-单开，2-双开，3-三开
	
	private Date beginDate;//有效期开始
	
	private Date endDate;//有效期结束
	
	private String remark;//备注
	
	private Date createDate;

	@ManyToOne
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
	

}
