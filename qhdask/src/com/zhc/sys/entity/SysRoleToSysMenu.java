package com.zhc.sys.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;


@Entity
@Table(name="sysroletosysmenu")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class SysRoleToSysMenu implements Serializable {
	
	private SysRoleToSysMenuId id;
	private String extendf1;

	public SysRoleToSysMenu() {
	}

	public SysRoleToSysMenu(SysRoleToSysMenuId id) {
		this.id = id;
	}

	public SysRoleToSysMenu(SysRoleToSysMenuId id, String extendf1) {
		this.id = id;
		this.extendf1 = extendf1;
	}

	@EmbeddedId
	public SysRoleToSysMenuId getId() {
		return this.id;
	}

	public void setId(SysRoleToSysMenuId id) {
		this.id = id;
	}

	public String getExtendf1() {
		return this.extendf1;
	}

	public void setExtendf1(String extendf1) {
		this.extendf1 = extendf1;
	}
}
