package com.zhc.games.entity;

import static javax.persistence.AccessType.PROPERTY;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

@Entity
@Table(name = "AccountDetail")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class AccountDetail extends AbstractEntityGenerate{
	
	private Account account;
	
	private Integer adindex;//第几个机器的账号
	
	private String content;//账号内容

	@ManyToOne(cascade = CascadeType.ALL)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getAdindex() {
		return adindex;
	}

	public void setAdindex(Integer adindex) {
		this.adindex = adindex;
	}

	@Column(columnDefinition="LONGTEXT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	

}
