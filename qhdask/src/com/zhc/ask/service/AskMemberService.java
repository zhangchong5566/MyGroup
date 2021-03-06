package com.zhc.ask.service;

import java.util.List;
import java.util.Map;

import com.zhc.ask.entity.AskMember;
import com.zhc.ask.web.action.form.SearchForm;
import com.zhc.sys.service.base.BaseJpaServiceInterf;
import com.zhc.sys.service.base.Pages;

public interface AskMemberService  extends BaseJpaServiceInterf {
	
	public static final String ID_NAME = "askMemberService";
	
	public List<AskMember> listMembers(AskMember bean,Pages pages);
	
	public AskMember login(String loginName,String password);
	
	/**
	 * 检查用户名是否存在，true:存在，false:不存在
	 * @param loginName
	 * @param noCheckId
	 * @return
	 */
	public boolean checkExistLoginName(String loginName,Long noCheckId);
	
	/**
	 * 查询推荐的专家
	 * @return
	 */
	public List<Map> listRecommendExpert();
	
	/**
	 * 查询所有专家
	 * @param form
	 * @param pages
	 * @return
	 */
	public List<Map> listExperts(SearchForm form,Pages pages);

}
