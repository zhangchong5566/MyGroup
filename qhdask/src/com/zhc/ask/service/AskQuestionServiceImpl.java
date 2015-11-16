package com.zhc.ask.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zhc.ask.entity.AskAnswer;
import com.zhc.ask.entity.AskQuestion;
import com.zhc.ask.web.action.form.SearchForm;
import com.zhc.sys.service.base.BaseJpaService;
import com.zhc.sys.service.base.Pages;

public class AskQuestionServiceImpl extends BaseJpaService  implements AskQuestionService {

	@Override
	public List<AskQuestion> listQuestions(SearchForm form, Pages pages) {
		
		StringBuffer jpql = new StringBuffer(" from AskQuestion q where q.hidden=1");
		List params = new ArrayList();
		if(form != null){
			if(form.getClsid() != null && form.getClsid() > 0){
				jpql.append(" and q.classify.id=?");
				params.add(form.getClsid());
			}
			if(StringUtils.isNotBlank(form.getKey())){
				jpql.append(" and (q.title like ? or q.askTag like ?)");
				params.add("%"+form.getKey()+"%");
				params.add("%"+form.getKey()+"%");
			}
			if(form.getStatus() != null && form.getStatus() > 0){
				jpql.append(" and q.status=?");
				params.add(form.getStatus());
			}
			if(form.getIsopen() != null && form.getIsopen() != 0){
				jpql.append(" and q.isopen=?");
				params.add(form.getIsopen());
			}
			if(form.getMemberId() != null && form.getMemberId() > 0){
				jpql.append(" and q.member.id=?");
				params.add(form.getMemberId());
			}
			if(form.getAnswerMemberId() != null && form.getAnswerMemberId() > 0){
				jpql.append(" and q.id in (select a.question.id from AskAnswer a where a.member.id=?)");
				params.add(form.getAnswerMemberId());
			}
		}
		
		return super.queryByJPQL("select count(q.id) "+jpql, jpql+" order by q.id desc", params.toArray(), pages);
	}

	@Override
	public List<AskAnswer> listAnswers(Long questionId) {
		
		return super.queryByJPQL("from AskAnswer a where a.hidden=1 and a.question.id=? order by a.id desc", new Object[]{questionId});
	}

	@Override
	public Map statQuestions() {
		
		String sql = "SELECT SUM(IF(q.status=2,1,0)) AS resolved,SUM(IF(q.status=1,1,0)) AS unresolved FROM ask_question q WHERE q.hidden=1";
		
		return super.querySingleBySQL2(sql, null);
	}

	@Override
	public List<Map> statHotQuestions(int size) {
		
		String sql = "SELECT q.id,q.title,c.name AS clsName,COUNT(a.id) AS ansCount FROM ask_answer a "
				+ "LEFT JOIN ask_question q ON q.id=a.question_id LEFT JOIN ask_classify c ON c.id=q.classify_id  WHERE q.hidden=1 "
				+ "GROUP BY q.id,q.title,c.name ORDER BY COUNT(a.id) DESC limit "+size;
		
		return super.queryBySQL2(sql);
	}

	

}
