package com.zhc.sys.entity;

import static javax.persistence.AccessType.PROPERTY;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

@Entity
@Table(name="sysusertosysrole")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class SysUserToSysRole extends AbstractEntityGenerate{

	private SysUser sysuser;
	
	private SysRole sysrole;
	
	@ManyToOne
	public SysUser getSysuser() {
		return sysuser;
	}

	public void setSysuser(SysUser sysuser) {
		this.sysuser = sysuser;
	}
	
	@ManyToOne
	public SysRole getSysrole() {
		return sysrole;
	}

	public void setSysrole(SysRole sysrole) {
		this.sysrole = sysrole;
	}
	
	
	
}
