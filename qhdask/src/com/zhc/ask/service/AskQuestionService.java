package com.zhc.ask.service;

import java.util.List;
import java.util.Map;

import com.zhc.ask.entity.AskAnswer;
import com.zhc.ask.entity.AskQuestion;
import com.zhc.ask.web.action.form.SearchForm;
import com.zhc.sys.service.base.BaseJpaServiceInterf;
import com.zhc.sys.service.base.Pages;

public interface AskQuestionService  extends BaseJpaServiceInterf {
	
	public static final String ID_NAME = "askQuestionService";
	
	public List<AskQuestion> listQuestions(SearchForm form,Pages pages);
	
	/**
	 * 查询问题的回答
	 * @param questionId
	 * @return
	 */
	public List<AskAnswer> listAnswers(Long questionId);
	
	/**
	 * 统计解决和未解决的问题数量
	 * @return
	 */
	public Map statQuestions();
	
	public List<Map> statHotQuestions(int size);
	

}
