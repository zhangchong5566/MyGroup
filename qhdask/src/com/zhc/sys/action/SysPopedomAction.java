package com.zhc.sys.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.sys.entity.SysPopedom;
import com.zhc.sys.service.SysPopedomService;


@Controller
@ParentPackage("json-default")
public class SysPopedomAction extends BaseAction {
	
	
	private static final long serialVersionUID = 7245895803661428977L;
	
	@Resource(name = SysPopedomService.ID_NAME)
	private SysPopedomService sysPopedomService;
	
	private String message;
	
	private List<SysPopedom> list;
	private SysPopedom popedom;
	
	@Action(value = "/sys/syspopedom", results = { @Result(name = SUCCESS, location = "/sys/syspopedom_list.jsp")})
	public String syspopedom(){
		
		return SUCCESS;
	}
	
	@Action(value = "/sys/listPopedom", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String listPopedom(){
		
		list = sysPopedomService.listPopedom(popedom, super.getReqPages());
	    
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/toEditPopedom", results = { @Result(name = SUCCESS, location = "/sys/syspopedom_edit.jsp")})
	public String toEditPopedom(){
		
		long id = super.getLongParamter("id", 0);
		if(id > 0){
			popedom = sysPopedomService.find(SysPopedom.class, id);
		}
		
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/savePopedom", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String savePopedom(){
		
		if(popedom.getId() != null && popedom.getId() > 0){
			
			SysPopedom bean = sysPopedomService.find(SysPopedom.class, popedom.getId());
			bean.setCode(popedom.getCode());
			bean.setDescription(popedom.getDescription());
			sysPopedomService.update(bean);
		}else{
			sysPopedomService.create(popedom);
		}
		
		message = "Success";
		
		return SUCCESS;
	}
	
	
	@Action(value = "/sys/deletePopedom", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String deletePopedom(){
		
		long id = super.getLongParamter("id", 0);
		
		if(id > 0){
			
			sysPopedomService.delete(SysPopedom.class, id);
			
		}
		
		message = "Success";
		
		return SUCCESS;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<SysPopedom> getList() {
		return list;
	}

	public void setList(List<SysPopedom> list) {
		this.list = list;
	}


	public SysPopedom getPopedom() {
		return popedom;
	}


	public void setPopedom(SysPopedom popedom) {
		this.popedom = popedom;
	}
	
	
	
	
	
	
}
