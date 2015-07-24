package com.zhc.sys.service;

import java.util.List;

import com.zhc.sys.entity.SysRole;
import com.zhc.sys.entity.SysRoleToSysMenu;
import com.zhc.sys.service.base.BaseJpaServiceInterf;
import com.zhc.sys.service.base.Pages;

public interface SysRoleService extends BaseJpaServiceInterf{
	
	public static final String ID_NAME = "sysRoleService";
	
	public List<SysRole> listRole(SysRole bean,Pages pages);
	
	/**
	 * 保存角色的菜单权限
	* @Title: saveRoleToMenu 
	* @Description: TODO
	* @param @param sysrole
	* @param @param rmList
	* @return void
	* @throws 
	* @date 2014年9月11日 下午2:36:02
	 */
	public void saveRoleToMenu(SysRole sysrole,List<SysRoleToSysMenu> rmList);
	
	
}
