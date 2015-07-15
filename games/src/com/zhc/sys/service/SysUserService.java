package com.zhc.sys.service;

import java.util.List;

import com.zhc.sys.entity.SysUser;
import com.zhc.sys.entity.SysUserToSysRole;
import com.zhc.sys.service.base.BaseJpaServiceInterf;
import com.zhc.sys.service.base.Pages;

/**
 * 运营用户管理
* @ClassName: SysUserService 
* @Description: TODO
* @author zhangchong
* @date 2014年7月24日 下午3:20:10 
*
 */
public interface SysUserService extends BaseJpaServiceInterf{
	
	public static final String ID_NAME = "sysUserService";
	
	/**
	 * 登录方法
	* @Title: login 
	* @Description: TODO
	* @param @param loginName
	* @param @param password
	* @param @return
	* @return SysUser
	* @throws 
	* @date 2014年7月24日 下午4:55:25
	 */
	public SysUser login(String loginName,String password);
	
	/**
	 * 查询
	* @Title: findList 
	* @Description: TODO
	* @param @param sysUser
	* @param @param pages
	* @param @return
	* @return List<SysUser>
	* @throws 
	* @date 2014年7月25日 上午9:50:52
	 */
	public List<SysUser> findList(SysUser sysUser,Pages pages);
	

	public List<SysUserToSysRole> findRolesByUser(Long userId);

	public void updateSysUserRoles(SysUser user,List<SysUserToSysRole> userRoleList);
	
}
