package com.zhc.games.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.zhc.games.action.form.ActionForm;
import com.zhc.games.entity.Account;
import com.zhc.games.entity.AccountDetail;
import com.zhc.sys.service.base.BaseJpaService;
import com.zhc.sys.service.base.Pages;
@Transactional
public class AccountServiceImpl extends BaseJpaService  implements AccountService {

	@Override
	public List<Account> listAccount(ActionForm form, Pages pages) {
		
		StringBuffer jpql = new StringBuffer();
		List params = new ArrayList<>();
		jpql.insert(0,"from Account where 1=1");
		
		if (StringUtils.isNotBlank(form.getSortName())) {
			jpql.append(" order by " + form.getSortName() + " "
					+ form.getSortType());
		}
		if(pages !=null){
			return super.queryByJPQL("select count(id) "+jpql, jpql.toString(), params.toArray(), pages);
		}else{
			return super.queryByJPQL(jpql.toString(),params.toArray());
		}
	}

	@Override
	public List<AccountDetail> listDetail(Long accountId) {
		return super.queryByJPQL("from AccountDetail where account.id=? order by adindex asc", new Object[]{accountId});
	}

	@Override
	public void deleteAllDetail(Long accountId) {
		super.executeJPQL("delete from AccountDetail where account.id=?",new Object[]{accountId});
		
	}

	@Override
	public void save(Account bean, List<AccountDetail> detailList) {
		
		if(bean.getId() == null || bean.getId() == 0){
			super.create(bean);
		}else{
			super.update(bean);
		}
		this.deleteAllDetail(bean.getId());
		
		super.saveAll(detailList);
		
	}

	@Override
	public Account getAccount(Long gameId, String userTag) {
		
		String jpql = "from Account a where game.id=? and userTag=?";
		List<Account> list =super.queryByJPQL(jpql, new Object[]{gameId,userTag});
		return list != null && list.size() > 0?list.get(0):null;
	}

	@Override
	public AccountDetail getDetail(Long accountId, Integer adindex) {
		
		String jpql = "from AccountDetail a where account.id=? and adindex=?";
		List<AccountDetail> list =super.queryByJPQL(jpql, new Object[]{accountId,adindex});
		return list != null && list.size() > 0?list.get(0):null;
	}
	
	
	
	
	
	
}
