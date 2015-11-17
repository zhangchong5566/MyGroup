package com.zhc.ask.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.ask.entity.AskMember;
import com.zhc.ask.entity.AskQuestion;
import com.zhc.ask.service.AskClassifyService;
import com.zhc.ask.service.AskMemberService;
import com.zhc.ask.service.AskQuestionService;
import com.zhc.ask.web.action.form.SearchForm;
import com.zhc.sys.service.base.Pages;

@Controller
@ParentPackage("json-default") 
public class HomeAction extends WebBaseAction{
	
	@Resource(name = AskMemberService.ID_NAME)
	private AskMemberService memberService;
	
	@Resource(name = AskClassifyService.ID_NAME)
	private AskClassifyService classifyService;
	
	@Resource(name = AskQuestionService.ID_NAME)
	private AskQuestionService questionService;
	
	private Long resolvedCount;//已解决
	private Long unResolvedCount;//未解决
	
	private List<Map> hotList;//热门回答
	
	private List<AskQuestion> resolvedList;//已解决问题
	
	private List<AskQuestion> unResolvedList;//待解决问题列表
	
	private List<Map> expertList;//推荐专家
	
	@Action(value = "/home", results = { @Result(name = SUCCESS, location = "/ask_home_nodirect.jsp") })
	public String home() {
		
		Map map = questionService.statQuestions();
		resolvedCount = Long.parseLong(map.get("resolved")+"");
		unResolvedCount = Long.parseLong(map.get("unresolved")+"");
		
		hotList = questionService.statHotQuestions(6);
		
		Pages pages = new Pages();
		pages.setPage(1);
		pages.setPageSize(10);
		SearchForm sf = new SearchForm();
		sf.setStatus(1);
		unResolvedList = questionService.listQuestions(sf, pages);
		
		pages.setPage(1);
		pages.setPageSize(10);
		sf.setStatus(2);
		resolvedList = questionService.listQuestions(sf, pages);
		
		expertList = memberService.listRecommendExpert();
		
		return SUCCESS;
	}

	public Long getResolvedCount() {
		return resolvedCount;
	}

	public void setResolvedCount(Long resolvedCount) {
		this.resolvedCount = resolvedCount;
	}

	public Long getUnResolvedCount() {
		return unResolvedCount;
	}

	public void setUnResolvedCount(Long unResolvedCount) {
		this.unResolvedCount = unResolvedCount;
	}

	public List<Map> getHotList() {
		return hotList;
	}

	public void setHotList(List<Map> hotList) {
		this.hotList = hotList;
	}

	public List<AskQuestion> getResolvedList() {
		return resolvedList;
	}

	public void setResolvedList(List<AskQuestion> resolvedList) {
		this.resolvedList = resolvedList;
	}

	public List<AskQuestion> getUnResolvedList() {
		return unResolvedList;
	}

	public void setUnResolvedList(List<AskQuestion> unResolvedList) {
		this.unResolvedList = unResolvedList;
	}

	public List<Map> getExpertList() {
		return expertList;
	}

	public void setExpertList(List<Map> expertList) {
		this.expertList = expertList;
	}

	
	
	
}
