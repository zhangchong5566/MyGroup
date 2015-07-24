package com.zhc.sys.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.sys.entity.SysMenu;
import com.zhc.sys.entity.SysPopedom;
import com.zhc.sys.entity.SysRole;
import com.zhc.sys.entity.SysRoleToSysMenu;
import com.zhc.sys.entity.SysRoleToSysMenuId;
import com.zhc.sys.service.SysMenuService;
import com.zhc.sys.service.SysPopedomService;
import com.zhc.sys.service.SysRoleService;

@Controller
@ParentPackage("json-default")
public class SysRoleAction extends BaseAction {
	
	private static final long serialVersionUID = -2563948508803895483L;
	
	@Resource(name=SysRoleService.ID_NAME)
	private SysRoleService sysRoleService;
	
	@Resource(name=SysPopedomService.ID_NAME)
	private SysPopedomService sysPopedomService;
	
	@Resource(name = SysMenuService.ID_NAME)
	private SysMenuService sysMenuService;
	
	private List<SysMenu> mlist;
	
	private List<SysRole> list;
	
	private Set<SysPopedom> popedomSet;
	
	private SysRole role;
	
	private String message;
	
	private String menuIds;
	
	@Action(value = "/sys/sysrole", results = { @Result(name = SUCCESS, location = "/sys/sysrole_list.jsp")})
	public String sysrole(){
		
		return SUCCESS;
	}
	
	@Action(value = "/sys/listRole", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String listRole(){
		
		list = sysRoleService.listRole(role, super.getReqPages());
	    
		return SUCCESS;
	}

	@Action(value = "/sys/toEditRole", results = { @Result(name = SUCCESS, location = "/sys/sysrole_edit.jsp")})
	public String toEditRole(){
		long id = super.getLongParamter("id", 0);
		if(id > 0){
			role = sysRoleService.find(SysRole.class, id);
		}
		return SUCCESS;
	}
	
	@Action(value = "/sys/saveRole", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveRole(){
		
		if(role.getId() != null && role.getId() > 0){
			
			SysRole bean = sysRoleService.find(SysRole.class, role.getId());
			bean.setName(role.getName());
			bean.setDescription(role.getDescription());
			
			sysRoleService.update(bean);
		}else{
			role.setType(1);
			sysRoleService.create(role);
		}
		
	    message = "Success";
		
		return SUCCESS;
	}
	
	@Action(value = "/sys/deleteRole", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String deleteRole(){
		
		long id = super.getLongParamter("id", 0);
		
		if(id > 0){
			try{
				sysRoleService.delete(SysRole.class, id);
				message = "Success";
			}catch(Exception ex){
				message = "Delete failed, please check whether there is a correlation data.";
			}
			
		}
		
		return SUCCESS;
	}
	
	
	
	@Action(value = "/sys/toRoleMenu", results = { @Result(name = SUCCESS, location = "/sys/sysrole_menu.jsp")})
	public String toRoleMenu(){
		long roleId = super.getLongParamter("roleId", 0);
		if(roleId > 0){
			role = sysRoleService.find(SysRole.class, roleId);
			Set<SysRoleToSysMenu> set = role.getSysMenus();
			
			StringBuffer str = new StringBuffer(",");
			
			for(SysRoleToSysMenu rm : set){
				str.append(rm.getId().getSysmenu().getId()+",");
			}
			menuIds = str.toString();
		}
		
		long rootId = super.getLongParamter("rootId", 1l);
	    mlist = sysMenuService.getTree(rootId);
		
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/saveRoleMenu", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveRoleMenu(){
		
		String[] mid_arr = request.getParameterValues("mid");
		long roleId = super.getLongParamter("roleId", 0);
		
		role = sysRoleService.find(SysRole.class, roleId);
		
		List<SysRoleToSysMenu> rmList = new ArrayList<SysRoleToSysMenu>();
		
		if(mid_arr != null){
			SysRoleToSysMenu rm = null;
			SysRoleToSysMenuId rmId = null;
			SysMenu menu = null;
			
			for(String mid : mid_arr){
				
				rmId = new SysRoleToSysMenuId();
				menu = sysMenuService.find(SysMenu.class, Long.parseLong(mid));
				rmId.setSysmenu(menu);
				rmId.setSysrole(role);
				
				rm = new SysRoleToSysMenu();
				rm.setId(rmId);
				rmList.add(rm);
			}
		}
		
		sysRoleService.saveRoleToMenu(role, rmList);
		
		message = "Success";
		
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/toRolePopedom", results = { @Result(name = SUCCESS, location = "/sys/sysrole_popedom.jsp")})
	public String toRolePopedom(){
		long roleId = super.getLongParamter("roleId", 0);
		if(roleId > 0){
			role = sysRoleService.find(SysRole.class, roleId);
			popedomSet = role.getSysPopedoms();
		}
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/saveRoleToPopedom", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveRoleToPopedom(){
		
		role = sysRoleService.find(SysRole.class, role.getId());
		String[] ids = request.getParameterValues("ids");
		
		Set<SysPopedom> pset = new HashSet<SysPopedom>();
		if(ids == null){
			role.setSysPopedoms(pset);
			sysRoleService.update(role);
		}else{
			for(String id : ids){
				pset.add(sysPopedomService.find(SysPopedom.class, Long.parseLong(id)));
			}
			role.setSysPopedoms(pset);
			sysRoleService.update(role);
		}
		message = "Success";
		return SUCCESS;
	}
	
	public List<SysRole> getList() {
		return list;
	}

	public void setList(List<SysRole> list) {
		this.list = list;
	}

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<SysMenu> getMlist() {
		return mlist;
	}

	public void setMlist(List<SysMenu> mlist) {
		this.mlist = mlist;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public Set<SysPopedom> getPopedomSet() {
		return popedomSet;
	}

	public void setPopedomSet(Set<SysPopedom> popedomSet) {
		this.popedomSet = popedomSet;
	}
	
	
	

}
