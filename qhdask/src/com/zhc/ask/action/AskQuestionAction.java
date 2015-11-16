package com.zhc.ask.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.ask.entity.AskAnswer;
import com.zhc.ask.entity.AskClassify;
import com.zhc.ask.entity.AskQuestion;
import com.zhc.ask.service.AskClassifyService;
import com.zhc.ask.service.AskQuestionService;
import com.zhc.ask.web.action.form.SearchForm;
import com.zhc.sys.action.BaseAction;
@Controller
@ParentPackage("json-default") 
public class AskQuestionAction extends BaseAction {
	
	@Resource(name = AskClassifyService.ID_NAME)
	private AskClassifyService classifyService;
	
	@Resource(name = AskQuestionService.ID_NAME)
	private AskQuestionService questionService;
	
	private List<AskClassify> clist;
	
	private List<AskQuestion> qlist;
	
	private List<AskAnswer> answers;
	
	private AskQuestion qbean;
	
	private SearchForm sf;
	
	private String message;
	
	@Action(value = "/manage/ask/toQuestionList", results = { @Result(name = SUCCESS, location = "/manage/ask/question_list.jsp") })
	public String toQuestionList() {
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/ask/listQuestions", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String listQuestions(){
		
		qlist = questionService.listQuestions(sf, super.getReqPages());
		
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/ask/delQuestion", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String delQuestion(){
		
		long id = super.getLongParamter("id", 0);
		if(id > 0){
			AskQuestion question = questionService.find(AskQuestion.class, id);
			question.setHidden(2);
			questionService.update(question);
			message ="Success";
		}
		
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/ask/showQuestion", results = { @Result(name = SUCCESS, location = "/manage/ask/question_show.jsp") })
	public String showQuestion() {
		
		long id = super.getLongParamter("id", 0);
		if(id > 0){
			qbean = questionService.find(AskQuestion.class, id);
			answers = questionService.listAnswers(id);
		}
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/ask/delAnswer", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String delAnswer(){
		
		long ansid = super.getLongParamter("ansid", 0);
		
		if(ansid > 0){
			AskAnswer answer = questionService.find(AskAnswer.class, ansid);
			answer.setHidden(2);
			questionService.update(answer);
			//较少回答的数量
			AskQuestion question = questionService.find(AskQuestion.class, answer.getQuestion().getId());
			if(question.getReplayCount()!=null && question.getReplayCount() > 0){
				question.setReplayCount(question.getReplayCount()-1);
				questionService.update(question);
			}
			message ="Success";
			
		}
		
		return SUCCESS;
	}
		
		

	public List<AskClassify> getClist() {
		return clist;
	}

	public void setClist(List<AskClassify> clist) {
		this.clist = clist;
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
