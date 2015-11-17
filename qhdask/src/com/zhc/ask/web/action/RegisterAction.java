package com.zhc.ask.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.affix.service.AffixService;
import com.zhc.ask.entity.AskMember;
import com.zhc.ask.service.AskMemberService;
import com.zhc.servlet.RandomCodeImage;
import com.zhc.util.Md5;

@Controller
@ParentPackage("json-default")
public class RegisterAction extends WebBaseAction {

	@Resource(name = AskMemberService.ID_NAME)
	private AskMemberService memberService;
	
	@Resource(name = AffixService.ID_NAME)
	private AffixService affixService;

	private String message;

	private AskMember m;

	@Action(value = "/register", results = { @Result(name = SUCCESS, type = "json", params = { "ignoreHierarchy", "false" }) })
	public String register() {

		String randomCode = super.getStr("randomCode", "");
		if (!randomCode.equals(session.getAttribute(RandomCodeImage.RANDOMCODENAME))) {
			message = "验证码输入错误！";
			return SUCCESS;
		}
		
		if(m != null){
			
			if(StringUtils.isBlank(m.getLoginName()) || StringUtils.isBlank(m.getPassword())){
				message = "输入有误！";
				return SUCCESS;
			}
			
			//检查用户名是否存在
			boolean checkExistLoginName = memberService.checkExistLoginName(m.getLoginName(), null);
			if(checkExistLoginName){
				message = "用户名已被占用，请重新输入！";
				return SUCCESS;
			}
			m.setJoinDate(new Date());
			m.setRole(1);
			m.setStatus(1);
			m.setRecommend(0);
			m.setPassword(Md5.getMD5Str(m.getPassword()));
			memberService.create(m);
			message = "Success";
		}else{
			message = "注册失败";
		}
		
		return SUCCESS;
	}
	
	
	@Action(value = "/register_expert", results = { @Result(name = SUCCESS, type = "json", params = { "ignoreHierarchy", "false" }) })
	public String register_expert() {

		String randomCode = super.getStr("randomCode", "");
		if (!randomCode.equals(session.getAttribute(RandomCodeImage.RANDOMCODENAME))) {
			message = "验证码输入错误！";
			return SUCCESS;
		}
		
		if(m != null){
			
			if(StringUtils.isBlank(m.getLoginName()) || StringUtils.isBlank(m.getPassword())){
				message = "输入有误！";
				return SUCCESS;
			}
			
			//检查用户名是否存在
			boolean checkExistLoginName = memberService.checkExistLoginName(m.getLoginName(), null);
			if(checkExistLoginName){
				message = "用户名已被占用，请重新输入！";
				return SUCCESS;
			}
			m.setJoinDate(new Date());
			m.setRole(2);
			m.setStatus(1);
			m.setRecommend(0);
			m.setPassword(Md5.getMD5Str(m.getPassword()));
			memberService.create(m);
			
			String objectId = super.getStr("objectId");
			if(StringUtils.isNotBlank(objectId)){
				affixService.updateAffixId(1, objectId, m.getId()+"");
			}
			
			message = "Success";
		}else{
			message = "注册失败";
		}
		
		return SUCCESS;
	}

	public AskMember getM() {
		return m;
	}

	public void setM(AskMember m) {
		this.m = m;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
