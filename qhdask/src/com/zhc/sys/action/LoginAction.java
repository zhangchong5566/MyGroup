package com.zhc.sys.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.sys.entity.SysMenu;
import com.zhc.sys.entity.SysPopedom;
import com.zhc.sys.entity.SysRoleToSysMenu;
import com.zhc.sys.entity.SysUser;
import com.zhc.sys.entity.SysUserToSysRole;
import com.zhc.sys.service.SysMenuService;
import com.zhc.sys.service.SysUserService;
import com.zhc.util.Md5;

@Controller
public class LoginAction extends BaseAction{
	private static final long serialVersionUID = -4927011491411100168L;
	
	@Resource(name = SysUserService.ID_NAME)
	private SysUserService sysUserService;
	
	@Resource(name = SysMenuService.ID_NAME)
	private SysMenuService sysMenuService;
	
	private SysUser sysUser;
	private String message;
	
	/**
	 * 运营人员 登录
	* @Title: login 
	* @Description: TODO
	* @param @return
	* @return String
	* @throws 
	* @date 2014年7月24日 下午5:05:06
	 */
	@Action(value = "/login", results = { @Result(name = SUCCESS, type="redirect",location = "/manage/index.do"),
			 @Result(name = "login", location = "/login.jsp")})
	public String login(){
		
		String loginName = super.getStr("loginName",null);
		String password = super.getStr("password",null);
		
		sysUser = sysUserService.login(loginName, Md5.getMD5Str(password));
		
		if(sysUser == null){
			message = "用户名或密码错误！";
			return "login";
		}
		
		List<String> popedoms = new ArrayList<String>();
		Set<Long> menuIds = new HashSet<Long>(); 
		
		List<SysUserToSysRole> userRoles = sysUserService.findRolesByUser(sysUser.getId());
		
		for(SysUserToSysRole r : userRoles){
			
			for(SysPopedom p : r.getSysrole().getSysPopedoms()){
				if(!popedoms.contains(p.getCode())){
					popedoms.add(p.getCode());
				}
			}
			
			for(SysRoleToSysMenu m : r.getSysrole().getSysMenus()){
				menuIds.add(m.getId().getSysmenu().getId());
			}
		}
		
		List<SysMenu> menuTree = sysMenuService.getTree(1, menuIds);
		
		menuTree.remove(0);
		
		super.setLoginSysUser(sysUser);
		super.setPopedom(popedoms);
		super.setMenu(menuTree);
		super.setLoginSysUser(sysUser);
		
		
		return SUCCESS;
	}
	
	@Action(value = "/manage/index", results = { @Result(name = SUCCESS,location = "/manage/index.jsp")})
	public String index(){
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/loginOut", results = { @Result(name = SUCCESS,type="redirect",location = "/manage/login.jsp")})
	public String loginOut(){
		session.invalidate();
		return SUCCESS;
	}
	
	

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
