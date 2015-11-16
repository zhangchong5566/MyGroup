package com.zhc.ask.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.ask.entity.AskMember;
import com.zhc.ask.service.AskMemberService;
import com.zhc.servlet.RandomCodeImage;
import com.zhc.util.Md5;

@Controller
@ParentPackage("json-default")
public class LoginAction extends WebBaseAction {
	
	@Resource(name = AskMemberService.ID_NAME)
	private AskMemberService memberService;

	private String loginName;

	private String password;
	
	private String message = "";
	
	private String errno = "";

	@Action(value = "/login", results = { @Result(name = SUCCESS,type="redirect", location = "/home.do"),
			@Result(name = "Error",type="redirect",location = "/login.jsp?error=${errno}")})
	public String login() {
		String randomCode = super.getStr("randomCode","");
		if(!randomCode.equals(session.getAttribute(RandomCodeImage.RANDOMCODENAME))){
			errno = "1";
			return "Error";
		}
		
		AskMember member = memberService.login(loginName, Md5.getMD5Str(password));
		if(member == null){
			errno = "2";
			return "Error";
		}
		
		member.setLastLoginDate(new Date());
		memberService.update(member);
		super.setLoginMember(member);
		return SUCCESS;
	}
	@Action(value = "/loginOut", results = { @Result(name = SUCCESS,type="redirect", location = "/home.do")})
	public String loginOut() {
		session.invalidate();
		return SUCCESS;
	}
	
	
	

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrno() {
		return errno;
	}
	public void setErrno(String errno) {
		this.errno = errno;
	}
	
	

}
