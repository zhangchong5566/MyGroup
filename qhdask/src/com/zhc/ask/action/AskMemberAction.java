package com.zhc.ask.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.mysql.fabric.xmlrpc.base.Member;
import com.zhc.affix.service.AffixService;
import com.zhc.ask.entity.AskMember;
import com.zhc.ask.service.AskMemberService;
import com.zhc.sys.action.BaseAction;
import com.zhc.util.DateUtil;
@Controller
@ParentPackage("json-default") 
public class AskMemberAction extends BaseAction {
	
	@Resource(name = AskMemberService.ID_NAME)
	private AskMemberService memberService;
	
	@Resource(name = AffixService.ID_NAME)
	private AffixService affixService;
	
	private List<AskMember> mlist;
	
	private AskMember member;
	
	private String message;
	
	@Action(value = "/manage/ask/toNomalMemberList", results = { @Result(name = SUCCESS, location = "/manage/ask/member_nomal_list.jsp") })
	public String toNomalMemberList() {
		return SUCCESS;
	}
	
	@Action(value = "/manage/ask/toExpertMemberList", results = { @Result(name = SUCCESS, location = "/manage/ask/member_expert_list.jsp") })
	public String toExpertMemberList() {
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/ask/listMember", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String listMember(){
		
		mlist = memberService.listMembers(member, super.getReqPages());
	    
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/ask/changeStatus", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String changeStatus(){
		
		long id = super.getLongParamter("id", 0);
		int status = super.getIntParamter("status", 0);
		
		if(id > 0 && status > 0){
			member = memberService.find(AskMember.class, id);
			member.setStatus(status);
			memberService.update(member);
		}
	    
		message = "Success";
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/ask/toEditNomarlMember", results = { @Result(name = SUCCESS, location = "/manage/ask/member_nomal_edit.jsp") })
	public String toEditNomarlMember() {
		long id = super.getLongParamter("id", 0);
		if(id > 0){
			member = memberService.find(AskMember.class, id);
		}
		return SUCCESS;
	}
	
	@Action(value = "/manage/ask/toEditExpretMember", results = { @Result(name = SUCCESS, location = "/manage/ask/member_expret_edit.jsp") })
	public String toEditExpretMember() {
		long id = super.getLongParamter("id", 0);
		if(id > 0){
			member = memberService.find(AskMember.class, id);
		}
		return SUCCESS;
	}
	
	
	
	@Action(value = "/manage/ask/saveMember", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveMember(){
		
		if(member != null && member.getId() > 0){
			AskMember bean = memberService.find(AskMember.class, member.getId());
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
			
			String objectId = super.getStr("objectId");
			if(StringUtils.isNotBlank(objectId) && !StringUtils.equals(bean.getId()+"", objectId)){
				affixService.updateAffixId(1, objectId, bean.getId()+"");
			}
			
		}
		
		message = "保存成功！";
		return SUCCESS;
	}

	public List<AskMember> getMlist() {
		return mlist;
	}

	public void setMlist(List<AskMember> mlist) {
		this.mlist = mlist;
	}

	public AskMember getMember() {
		return member;
	}

	public void setMember(AskMember member) {
		this.member = member;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
