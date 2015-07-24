package com.zhc.sys.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;

import com.zhc.sys.entity.SysMenu;
import com.zhc.sys.service.SysMenuService;

@Controller
@ParentPackage("json-default")  
public class SysMenuAction extends BaseAction {

	private static final long serialVersionUID = 3511985550561650389L;
	
	@Resource(name = SysMenuService.ID_NAME)
	private SysMenuService sysMenuService;
	
	private List<SysMenu> mlist;
	
	private SysMenu menu;
	
	private String message;
	
	@Action(value = "/sys/listMenu", results = { @Result(name = SUCCESS, location = "/sys/sysmenu_list.jsp")})
	public String listMenu(){
		
		long rootId = super.getLongParamter("rootId", 1l);
	    mlist = sysMenuService.getTree(rootId);
	    
		return SUCCESS;
	}

	
	
	@Action(value = "/sys/toEditMenu", results = { @Result(name = SUCCESS, location = "/sys/sysmenu_edit.jsp")})
	public String toEditMenu(){
		
		long mid = super.getLongParamter("mid", 0l);
		
		if(mid > 0){
			menu = sysMenuService.find(SysMenu.class, mid);
		}
	    
		return SUCCESS;
	}
	
	@Action(value = "/sys/saveMenu", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveMenu(){
		
		Long id = super.getLongParamter("id", 0);
		Long parentId = super.getLongParamter("parentId",0);
		String name = super.getStr("name",null);
		String link = super.getStr("link",null);
		Long orderBy = super.getLongParamter("orderBy", 0); 
		
		Date now = new Date();
		
		if(id > 0){
			menu = sysMenuService.find(SysMenu.class, id);
			menu.setUpdateDate(now);
			menu.setEname(name);
			menu.setName(name);
			menu.setAlias(name);
			menu.setLink(link);
			menu.setOrderBy(orderBy);
			sysMenuService.update(menu);
		}else{
			menu = new SysMenu();
			menu.setCreateDate(now);
			menu.setUpdateDate(now);
			menu.setEname(name);
			menu.setName(name);
			menu.setAlias(name);
			menu.setLink(link);
			menu.setOrderBy(orderBy);
			SysMenu parentObj = sysMenuService.find(SysMenu.class, parentId);
			menu.setParentObj(parentObj);
			sysMenuService.create(menu);
		}
		
		message = "Success";
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/deleteMenu", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String deleteMenu(){
		
		long mid = super.getLongParamter("mid", 0l);
		
		if(mid > 0){
			try {
				sysMenuService.delete(SysMenu.class, mid);
				message = "Success";
			} catch (Exception e) {
				message = "Delete failed, please check whether there is a correlation data.";
			}
			
			
		}
		
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public List<SysMenu> getMlist() {
		return mlist;
	}


	public void setMlist(List<SysMenu> mlist) {
		this.mlist = mlist;
	}


	@JSON(serialize=false)
	public SysMenu getMenu() {
		return menu;
	}



	public void setMenu(SysMenu menu) {
		this.menu = menu;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
