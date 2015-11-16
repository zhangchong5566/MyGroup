package com.zhc.ask.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.ask.entity.AskMember;
import com.zhc.ask.entity.AskQuestion;
import com.zhc.ask.service.AskMemberService;
import com.zhc.ask.service.AskQuestionService;
import com.zhc.ask.web.action.form.SearchForm;
import com.zhc.sys.service.base.Pages;
import com.zhc.util.Md5;

@Controller
@ParentPackage("json-default")
public class UCenterAction extends WebBaseAction {
	
	@Resource(name = AskQuestionService.ID_NAME)
	private AskQuestionService questionService;
	
	@Resource(name = AskMemberService.ID_NAME)
	private AskMemberService memberService;
	
	private List<AskQuestion> qlist;
	
	private AskMember member;
	
	private SearchForm sf;
	
	private String message;
	

	
	@Action(value = "/ucenter/myask", results = { @Result(name = SUCCESS, location = "/ucenter/myask.jsp") })
	public String myask() {
		
		Pages pages = super.getReqPages();
		pages.setPageSize(25);
		if(sf == null){
			sf = new SearchForm();
		}
		AskMember loginMember = super.getLoginMember();
		
		sf.setMemberId(loginMember.getId());
		
		qlist = questionService.listQuestions(sf, pages);
		
		return SUCCESS;
	}
	
	
	@Action(value = "/ucenter/delQuestion", results = { @Result(name = SUCCESS, type = "json", params = { "ignoreHierarchy", "false" }) })
	public String delQuestion() {
		long id = super.getLongParamter("id", 0);
		if(id > 0){
			
			AskQuestion question = questionService.find(AskQuestion.class, id);
			//检查提问人是不是和登录用户是同一人
			if(super.getLoginMember().getId() != question.getMember().getId().intValue()){
				message = "Error";
				return SUCCESS;
			}
			
			question.setHidden(2);
			questionService.update(question);
			message = "Success";
		}
		
		return SUCCESS;
	}
	
	
	@Action(value = "/ucenter/myanswer", results = { @Result(name = SUCCESS, location = "/ucenter/myanswer.jsp") })
	public String myanswer() {
		
		Pages pages = super.getReqPages();
		pages.setPageSize(25);
		if(sf == null){
			sf = new SearchForm();
		}
		AskMember loginMember = super.getLoginMember();
		
		sf.setAnswerMemberId(loginMember.getId());
		
		qlist = questionService.listQuestions(sf, pages);
		
		return SUCCESS;
	}

	
	@Action(value = "/ucenter/myaccount", results = { @Result(name = SUCCESS, location = "/ucenter/myaccount.jsp") })
	public String myaccount() {
		
		member = memberService.find(AskMember.class, super.getLoginMember().getId());
		
		return SUCCESS;
	}
	
	@Action(value = "/ucenter/saveAccount", results = { @Result(name = SUCCESS, type = "json", params = { "ignoreHierarchy", "false" }) })
	public String saveAccount() {
		
		if(member!= null){
			
			if(member.getId()!=null && member.getId() == super.getLoginMember().getId().longValue()){
				
				AskMember bean = memberService.find(AskMember.class,member.getId());
				
				bean.setLoginName(member.getLoginName());
				bean.setTrueName(member.getTrueName());
				bean.setEmail(member.getEmail());
				bean.setSex(member.getSex());
				bean.setTelephone(member.getTelephone());
				bean.setAddress(member.getAddress());
				bean.setBirthdate(member.getBirthdate());
				bean.setCompany(member.getCompany());
				bean.setCurrProfessional(member.getCurrProfessional());
				bean.setCurrYears(member.getCurrYears());
				bean.setEducation(member.getEducation());
				bean.setExpertiseArea(member.getExpertiseArea());
				bean.setExpretTitle(member.getExpretTitle());
				bean.setJobTitle(member.getJobTitle());
				bean.setPersonProfile(member.getPersonProfile());
				bean.setProfessional(member.getProfessional());
				bean.setQqWeChart(member.getQqWeChart());
				bean.setSchool(member.getSchool());
				bean.setServicefall(member.getServicefall());
				
				memberService.update(bean);
				message = "Success";
			}else{
				message = "修改失败！";
			}
			
		}
		
		return SUCCESS;
	}
	
	
	@Action(value = "/ucenter/mypwd", results = { @Result(name = SUCCESS, location = "/ucenter/mypwd.jsp") })
	public String mypwd() {
		
		return SUCCESS;
	}
	
	
	@Action(value = "/ucenter/savePassword", results = { @Result(name = SUCCESS, type = "json", params = { "ignoreHierarchy", "false" }) })
	public String savePassword() {
		
		long mid = super.getLongParamter("mid", 0);
		
		if(mid > 0){
			if(mid == super.getLoginMember().getId()){
				
				String oldPassword = super.getStr("oldPassword");
				String newPassword = super.getStr("newPassword");
				String confirmPassword = super.getStr("confirmPassword");
				
				if(!StringUtils.equals(newPassword, confirmPassword)){
					message = "两次密码输入不一致";
					return SUCCESS;
				}
				
				AskMember bean = memberService.find(AskMember.class,mid);
				
				if(!StringUtils.equals(Md5.getMD5Str(oldPassword), bean.getPassword())){
					message = "旧密码输入错误";
					return SUCCESS;
				}
				
				bean.setPassword(Md5.getMD5Str(newPassword));
				
				memberService.update(bean);
				
				super.setLoginMember(bean);
				message = "Success";
			}else{
				message = "发生错误";
			}
		}
		
		
		return SUCCESS;
	}

	public List<AskQuestion> getQlist() {
		return qlist;
	}


	public void setQlist(List<AskQuestion> qlist) {
		this.qlist = qlist;
	}


	public SearchForm getSf() {
		return sf;
	}


	public void setSf(SearchForm sf) {
		this.sf = sf;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public AskMember getMember() {
		return member;
	}


	public void setMember(AskMember member) {
		this.member = member;
	}
	
	
	
}
