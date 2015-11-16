package com.zhc.ask.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.ask.entity.AskAnswer;
import com.zhc.ask.entity.AskClassify;
import com.zhc.ask.entity.AskMember;
import com.zhc.ask.entity.AskQuestion;
import com.zhc.ask.service.AskQuestionService;
import com.zhc.servlet.RandomCodeImage;

@Controller
@ParentPackage("json-default")
public class QuestionAction extends WebBaseAction {

	@Resource(name = AskQuestionService.ID_NAME)
	private AskQuestionService questionService;
	
	private AskQuestion qbean;
	
	private List<AskAnswer> answers;
	
	private String message;
	
	
	@Action(value = "/answer/toask", results = { @Result(name = SUCCESS, location = "/answer/ask.jsp"),
			@Result(name = "home",type="redirect", location = "/home.do?errno=101")})
	public String toask() throws IOException {
		if(super.getLoginMember().getStatus() == 1){
			return "home";
		}
		
		long id = super.getLongParamter("id", 0);
		
		if(id > 0){
			
			qbean = questionService.find(AskQuestion.class, id);
			if(qbean != null && qbean.getMember().getId() != super.getLoginMember().getId().intValue()){
				qbean = null;
			}
			
		}
		
		return SUCCESS;
	}
	
	
	@Action(value = "/answer/saveAsk", results = { @Result(name = SUCCESS, type = "json", params = { "ignoreHierarchy", "false" }) })
	public String saveAsk() {
		
		String randomCode = super.getStr("randomCode", "");
		if (!randomCode.equals(session.getAttribute(RandomCodeImage.RANDOMCODENAME))) {
			message = "验证码输入错误！";
			return SUCCESS;
		}
		long id = super.getLongParamter("id", 0);
		long clsId = super.getLongParamter("clsId", 0);
		String title = super.getStr("title");
		String editorValue = super.getStr("editorValue");
		String askTag = super.getStr("askTag");
		int isopen = super.getIntParamter("isopen", 1);
		
		if(clsId > 0 && StringUtils.isNotBlank(title)){
			
			AskClassify cls = questionService.find(AskClassify.class, clsId);
			AskQuestion question = null;
			if(id > 0){
				question = questionService.find(AskQuestion.class, id);
				if(question.getMember().getId() != super.getLoginMember().getId().intValue()){
					message = "Error";
					return SUCCESS;
				}
			}else{
				question = new AskQuestion();
			}
			
			question.setClassify(cls);
			question.setTitle(title);
			question.setDescription(editorValue);
			question.setAskTag(askTag);
			question.setCreateDate(new Date());
			question.setIsopen(isopen);
			question.setHidden(1);
			question.setMember(super.getLoginMember());
			question.setReplayCount(0);
			question.setViewCount(0l);
			question.setStatus(1);
			
			questionService.create(question);
			
			message = "Success";
		}else{
			message = "信息不全!";
		}
		
		return SUCCESS;
	}

	
	@Action(value = "/sq", results = { @Result(name = SUCCESS, location = "/show_question.jsp")})
	public String sq() throws IOException {
		
		long qid = super.getLongParamter("qid", 0);
		if(qid > 0){
			qbean = questionService.find(AskQuestion.class, qid);
			answers = questionService.listAnswers(qid);
			qbean.setViewCount(qbean.getViewCount() + 1);
			questionService.update(qbean);
		}
		
		return SUCCESS;
	}
	
	
	@Action(value = "/answer/saveAnswer", results = { @Result(name = SUCCESS, type = "json", params = { "ignoreHierarchy", "false" }) })
	public String saveAnswer() {
		
		String randomCode = super.getStr("randomCode", "");
		if (!randomCode.equals(session.getAttribute(RandomCodeImage.RANDOMCODENAME))) {
			message = "验证码输入错误！";
			return SUCCESS;
		}
		
		long qid = super.getLongParamter("qid", 0);
		String editorValue = super.getStr("editorValue");
		
		if(qid > 0 && StringUtils.isNotBlank(editorValue)){
			
			Date now = new Date();
			AskQuestion question = questionService.find(AskQuestion.class, qid);
			
			AskAnswer answer = new AskAnswer();
			answer.setAnswerContent(editorValue);
			answer.setHidden(1);
			answer.setStatus(1);
			answer.setMember(super.getLoginMember());
			answer.setQuestion(question);
			answer.setCreateDate(now);
			questionService.create(answer);
			
			question.setLastReplayDate(now);
			question.setReplayCount(question.getReplayCount()+1);
			questionService.update(question);
			
			message = "Success";
			
		}else{
			message = "请输入内容";
		}
		
		return SUCCESS;
	}
	
	@Action(value = "/answer/caina", results = { @Result(name = SUCCESS, type = "json", params = { "ignoreHierarchy", "false" }) })
	public String caina() {
		long ansid = super.getLongParamter("ansid", 0);
		if(ansid > 0){
			
			AskMember member = super.getLoginMember();
			AskAnswer answer = questionService.find(AskAnswer.class, ansid);
			AskQuestion question = questionService.find(AskQuestion.class, answer.getQuestion().getId());
			//检查提问人是不是和登录用户是同一人
			if(member.getId() != question.getMember().getId().intValue()){
				message = "Error";
				return SUCCESS;
			}
			
			answer.setStatus(2);
			questionService.update(answer);
			question.setStatus(2);
			questionService.update(question);
			message = "Success";
		}
		
		return SUCCESS;
	}
	

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public AskQuestion getQbean() {
		return qbean;
	}


	public void setQbean(AskQuestion qbean) {
		this.qbean = qbean;
	}


	public List<AskAnswer> getAnswers() {
		return answers;
	}


	public void setAnswers(List<AskAnswer> answers) {
		this.answers = answers;
	}


	
	
	
}
