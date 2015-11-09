package com.zhc.ask.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.ask.service.AskMemberService;
import com.zhc.sys.action.BaseAction;
@Controller
@ParentPackage("json-default") 
public class AskMemberAction extends BaseAction {
	
	@Resource(name = AskMemberService.ID_NAME)
	private AskMemberService memberService;
	
	@Action(value = "/manage/ask/toNomalMemberList", results = { @Result(name = SUCCESS, location = "/manage/ask/member_nomal_list.jsp") })
	public String toNomalMemberList() {
		return SUCCESS;
	}
	
}
