package com.zhc.ask.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.zhc.ask.entity.AskMember;
import com.zhc.sys.service.base.BaseJpaService;
import com.zhc.sys.service.base.Pages;

public class AskMemberServiceImpl extends BaseJpaService  implements AskMemberService {

	@Override
	public List<AskMember> listMembers(AskMember bean, Pages pages) {
		
		StringBuffer jpql = new StringBuffer();
		List params = new ArrayList<>();
		if(bean != null){
			
			if(StringUtils.isNotBlank(bean.getLoginName())){
				if(jpql.length() > 0){
					jpql.append(" AND ");
				}
				jpql.append("(m.loginName like ? OR m.trueName like ? OR m.email like ?)");
				params.add("%"+bean.getLoginName()+"%");
				params.add("%"+bean.getLoginName()+"%");
				params.add("%"+bean.getLoginName()+"%");
			}
			
			if(bean.getRole() != null && bean.getRole() > 0){
				if(jpql.length() > 0){
					jpql.append(" AND ");
				}
				jpql.append("m.role=?");
				params.add(bean.getRole());
			}
			
		}
		jpql.insert(0, "FROM AskMember m WHERE ");
		jpql.append(" ORDER BY m.id DESC");
		
		return super.queryByJPQL("select count(m.id) "+jpql, jpql.toString(), params.toArray(), pages);
	}

	@Override
	public AskMember login(String loginName, String password) {
		
		String jpql = "From AskMember m where (m.loginName=? or m.email=?) and m.password=?";
		List<AskMember> list = super.queryByJPQL(jpql, new Object[]{loginName,loginName,password});
		return list != null && list.size() > 0?list.get(0):null;
	}

	@Override
	public boolean checkExistLoginName(String loginName, Long noCheckId) {
		
		String where = " loginName=? ";
		if(noCheckId != null && noCheckId>0){
			where += " and id<>"+noCheckId;
		}
		long count = super.getCountByWhere(AskMember.class, where, new Object[]{loginName});
		
		return count>0?true:false;
	}

	

}
