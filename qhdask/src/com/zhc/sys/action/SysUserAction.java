package com.zhc.sys.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.sys.entity.SysRole;
import com.zhc.sys.entity.SysUser;
import com.zhc.sys.entity.SysUserToSysRole;
import com.zhc.sys.service.SysRoleService;
import com.zhc.sys.service.SysUserService;
import com.zhc.util.Md5;

@Controller
@ParentPackage("json-default")  
public class SysUserAction extends BaseAction {

	private static final long serialVersionUID = -4927011491411100168L;
	
	@Resource(name = SysUserService.ID_NAME)
	private SysUserService sysUserService;
	
	@Resource(name=SysRoleService.ID_NAME)
	private SysRoleService sysRoleService;
	
	private SysUser sysUser;
	
	private List<SysUser> list;
	
	private List<SysRole> roleList;
	
	private List<SysUserToSysRole> userRoleList;
	
	private String message;
	
	@Action(value = "/sys/listSysUser", results = { @Result(name = SUCCESS, location = "/sys/sysuser_list.jsp")})
	public String listSysUser(){
		
		//list = sysUserService.findList(sysUser, super.getReqPages());
		
		return SUCCESS;
	}
	
	
	
	/**
	 * 返回json的方法不要使用set和get开头，否则会导致方法被执行两次，因为会先序列化一次
	* @Title: listSysUserJsonData 
	* @Description: TODO
	* @param @return
	* @return String
	* @throws 
	* @date 2014年8月5日 下午5:39:05
	 */
	@Action(value = "/sys/listSysUserJsonData", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String listSysUserJsonData(){
		
		list = sysUserService.findList(sysUser, super.getReqPages());
		
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/toEditUser", results = { @Result(name = SUCCESS, location = "/sys/sysuser_edit.jsp")})
	public String toEditUser(){
		
		long id = super.getLongParamter("id", 0);
		
		if(id > 0){
			sysUser = sysUserService.find(SysUser.class,id);
		}
		
		return SUCCESS;
	}
	
	@Action(value = "/sys/saveSysUser", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveSysUser(){
		
		String[] roleId_arr = request.getParameterValues("roleId");//还没有保存用户角色
		
		SysUser bean = null;
		Date now = new Date();
		
		if(sysUser.getId() != null && sysUser.getId() > 0){
			bean = sysUserService.find(SysUser.class,sysUser.getId());
			bean.setLoginName(sysUser.getLoginName());
			bean.setTrueName(sysUser.getTrueName());
			bean.setEmail(sysUser.getEmail());
			bean.setPhone(sysUser.getPhone());
			bean.setStatus(sysUser.getStatus());
			sysUserService.update(bean);
		}else{
			bean = new SysUser();
			bean.setLoginName(sysUser.getLoginName());
			bean.setPassword(Md5.getMD5Str(sysUser.getPassword()));
			bean.setTrueName(sysUser.getTrueName());
			bean.setEmail(sysUser.getEmail());
			bean.setPhone(sysUser.getPhone());
			bean.setStatus(sysUser.getStatus());
			bean.setCreateDate(now);
			sysUserService.create(bean);
		}
		
		message = "Success";
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/toUserRoles", results = { @Result(name = SUCCESS, location = "/sys/sysuser_role.jsp")})
	public String toUserRoles(){
		
		long id = super.getLongParamter("id", 0);
		
		if(id > 0){
			sysUser = sysUserService.find(SysUser.class,id);
		}
		
		roleList = sysRoleService.listRole(null, null);
		userRoleList = sysUserService.findRolesByUser(id);
		
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/saveUserRoles", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveUserRoles(){
		
		String[] roleId_arr = request.getParameterValues("roleId");
		
		sysUser = sysUserService.find(SysUser.class,sysUser.getId());
		userRoleList = new ArrayList<SysUserToSysRole>();
		
		SysUserToSysRole bean = null;
		
		for(String roleId : roleId_arr){
			bean = new SysUserToSysRole();
			bean.setSysrole(sysRoleService.find(SysRole.class, Long.parseLong(roleId)));
			bean.setSysuser(sysUser);
			userRoleList.add(bean);
		}
		
		sysUserService.updateSysUserRoles(sysUser, userRoleList);
		
		message = "Success";
		
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/toResetPassword", results = { @Result(name = SUCCESS, location = "/sys/reset_password.jsp")})
	public String toResetPassword(){
		
		return SUCCESS;
	}

	
	@Action(value = "/sys/resetPassword", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String resetPassword(){
		
		long id = super.getLongParamter("id", 0);
		String password = super.getStr("password");
		
		sysUser = sysUserService.find(SysUser.class,id);
		sysUser.setPassword(Md5.getMD5Str(password));
		
		sysUserService.update(sysUser);
		message = "Success";
		
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/toChangePwd", results = { @Result(name = SUCCESS, location = "/sys/change_password.jsp")})
	public String toChangePwd(){
		
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/changePwd", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String changePwd(){
		
		sysUser = super.getLoginSysUser();
		String oldPwd = super.getStr("oldPwd");
		String newPwd = super.getStr("newPwd");
		
		if(!StringUtils.equals(Md5.getMD5Str(oldPwd),sysUser.getPassword())){
			message = "Wrong old password entered";
		}else{
			sysUser.setPassword(Md5.getMD5Str(newPwd));
			sysUserService.update(sysUser);
			message = "Success";
		}
		
		return SUCCESS;
	}
	
	public SysUser getSysUser() {
		return sysUser;
	}


	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}


	public List<SysUser> getList() {
		return list;
	}


	public void setList(List<SysUser> list) {
		this.list = list;
	}



	public List<SysRole> getRoleList() {
		return roleList;
	}



	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}
	
	



	public List<SysUserToSysRole> getUserRoleList() {
		return userRoleList;
	}



	public void setUserRoleList(List<SysUserToSysRole> userRoleList) {
		this.userRoleList = userRoleList;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}




	
}
