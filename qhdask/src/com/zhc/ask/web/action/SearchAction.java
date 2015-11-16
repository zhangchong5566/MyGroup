package com.zhc.ask.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.ask.entity.AskClassify;
import com.zhc.ask.entity.AskMember;
import com.zhc.ask.entity.AskQuestion;
import com.zhc.ask.service.AskClassifyService;
import com.zhc.ask.service.AskQuestionService;
import com.zhc.ask.web.action.form.SearchForm;
import com.zhc.sys.service.base.Pages;

@Controller
@ParentPackage("json-default")
public class SearchAction extends WebBaseAction{
	
	@Resource(name = AskClassifyService.ID_NAME)
	private AskClassifyService classifyService;
	
	@Resource(name = AskQuestionService.ID_NAME)
	private AskQuestionService questionService;
	
	private SearchForm sf;
	
	private List<AskQuestion> qlist;
	
	private List<AskClassify> clsList;
	
	@Action(value = "/questions", results = { @Result(name = SUCCESS, location = "/questions.jsp") })
	public String questions() {
		Pages pages = super.getReqPages();
		pages.setPageSize(25);
		if(sf == null){
			sf = new SearchForm();
		}
		AskMember loginMember = super.getLoginMember();
		//只有审核通过的专家可以查看不公开的问题
		if(loginMember != null && loginMember.getRole() == 2 && loginMember.getStatus() == 2){
			sf.setIsopen(0);
		}else{
			sf.setIsopen(1);
		}
		
		qlist = questionService.listQuestions(sf, pages);
		
		return SUCCESS;
		
	}
	
	/**
	 * 获取子分类
	 * @return
	 */
	@Action(value = "/subClassify", results = { @Result(name = SUCCESS, type = "json", params = { "ignoreHierarchy", "false" }) })
	public String subClassify() {
		
		long pid = super.getLongParamter("pid", 0);
		if(pid > 0){
			clsList = classifyService.getClassifysByParentId(pid);
		}
		
		return SUCCESS;
	}

	public SearchForm getSf() {
		return sf;
	}

	public void setSf(SearchForm sf) {
		this.sf = sf;
	}

	public List<AskQuestion> getQlist() {
		return qlist;
	}

	public void setQlist(List<AskQuestion> qlist) {
		this.qlist = qlist;
	}

	public List<AskClassify> getClsList() {
		return clsList;
	}

	public void setClsList(List<AskClassify> clsList) {
		this.clsList = clsList;
	}

	
	
	

}
