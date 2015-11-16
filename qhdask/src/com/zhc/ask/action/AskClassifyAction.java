package com.zhc.ask.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.ask.entity.AskClassify;
import com.zhc.ask.service.AskClassifyService;
import com.zhc.ask.web.action.WebBaseAction;
import com.zhc.sys.action.BaseAction;

@Controller
@ParentPackage("json-default") 
public class AskClassifyAction extends BaseAction {

	@Resource(name = AskClassifyService.ID_NAME)
	private AskClassifyService classifyService;

	private List<AskClassify> clist;

	private AskClassify classify;

	private String message;

	public List<AskClassify> getClist() {
		return clist;
	}

	public void setClist(List<AskClassify> clist) {
		this.clist = clist;
	}
	
	

	public AskClassify getClassify() {
		return classify;
	}

	public void setClassify(AskClassify classify) {
		this.classify = classify;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Action(value = "/manage/ask/classifyList", results = { @Result(name = SUCCESS, location = "/manage/ask/classify_list.jsp") })
	public String toAskClassifyList() {
		long rootId = super.getLongParamter("rootId", 1l);
		clist = classifyService.getTree(rootId);
		return SUCCESS;
	}

	@Action(value = "/manage/ask/toEditClassify", results = { @Result(name = SUCCESS, location = "/manage/ask/classify_edit.jsp") })
	public String toEditClassify() {

		long cid = super.getLongParamter("cid", 0l);

		if (cid > 0) {
			classify = classifyService.find(AskClassify.class, cid);
		}

		return SUCCESS;
	}
	
	
	@Action(value = "/manage/ask/saveClassify", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveClassify(){
		
		Long id = super.getLongParamter("id", 0);
		Long parentId = super.getLongParamter("parentId",0);
		String name = super.getStr("name",null);
		String link = super.getStr("link",null);
		Long orderBy = super.getLongParamter("orderBy", 0); 
		
		Date now = new Date();
		
		if(id > 0){
			classify = classifyService.find(AskClassify.class, id);
			classify.setUpdateDate(now);
			classify.setEname(name);
			classify.setName(name);
			classify.setAlias(name);
			classify.setLink(link);
			classify.setOrderBy(orderBy);
			classifyService.update(classify);
		}else{
			classify = new AskClassify();
			classify.setCreateDate(now);
			classify.setUpdateDate(now);
			classify.setEname(name);
			classify.setName(name);
			classify.setAlias(name);
			classify.setLink(link);
			classify.setOrderBy(orderBy);
			AskClassify parentObj = classifyService.find(AskClassify.class, parentId);
			classify.setParentObj(parentObj);
			classifyService.create(classify);
		}
		
		WebBaseAction.updateContextClassify(session.getServletContext());
		message = "保存成功！";
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/ask/deleteClassify", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String deleteClassify(){
		
		long cid = super.getLongParamter("cid", 0l);
		
		if(cid > 0){
			try {
				classifyService.delete(AskClassify.class, cid);
				WebBaseAction.updateContextClassify(request.getServletContext());
				message = "删除成功！";
			} catch (Exception e) {
				message = "删除失败，请检查是否有下级分类，如果有，必须先删除下级.";
			}
		}
		
		return SUCCESS;
	}

}
