package com.zhc.games.service;

import java.util.List;

import com.zhc.games.action.form.ActionForm;
import com.zhc.games.entity.Account;
import com.zhc.games.entity.AccountDetail;
import com.zhc.sys.service.base.BaseJpaServiceInterf;
import com.zhc.sys.service.base.Pages;

public interface AccountService  extends BaseJpaServiceInterf{
	
	public static final String ID_NAME = "accountService";
	
	public List<Account> listAccount(ActionForm form,Pages pages);
	
	public List<AccountDetail> listDetail(Long accountId);
	
	public void deleteAllDetail(Long accountId);
	
	public void save(Account bean,List<AccountDetail> detailList);
	
	public Account getAccount(Long gameId,String userTag);
	
	public AccountDetail getDetail(Long accountId,Integer adindex);

}
