package com.zhc.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * SysRoleToSysMenu的联合主键
* @ClassName: SysRoleToSysMenuId 
* @Description: TODO
* @author zhangchong
* @date 2014年9月9日 下午3:02:43 
*
 */
@Embeddable
public class SysRoleToSysMenuId implements Serializable {
	private SysRole sysrole;
	private SysMenu sysmenu;

	public SysRoleToSysMenuId() {
	}

	public SysRoleToSysMenuId(SysRole sysrole, SysMenu sysmenu) {
		this.sysrole = sysrole;
		this.sysmenu = sysmenu;
	}

	@ManyToOne
	@JoinColumn(name = "sysrole_id",  nullable = false, columnDefinition = "bigint(20)") 
	public SysRole getSysrole() {
		return this.sysrole;
	}

	public void setSysrole(SysRole sysrole) {
		this.sysrole = sysrole;
	}

	@ManyToOne
	@JoinColumn(name = "sysmenu_id",  nullable = false, columnDefinition = "bigint(20)") 
	public SysMenu getSysmenu() {
		return this.sysmenu;
	}

	public void setSysmenu(SysMenu sysmenu) {
		this.sysmenu = sysmenu;
	}
}
