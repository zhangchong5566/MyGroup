package com.zhc.games.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.games.action.form.ActionForm;
import com.zhc.games.entity.Account;
import com.zhc.games.entity.AccountDetail;
import com.zhc.games.entity.Game;
import com.zhc.games.entity.SerialNumber;
import com.zhc.games.service.AccountService;
import com.zhc.games.service.GameService;
import com.zhc.sys.action.BaseAction;
import com.zhc.util.DateUtil;
@Controller
@ParentPackage("json-default")  
public class AccountAction extends BaseAction {
	
	@Resource(name=GameService.ID_NAME)
	private GameService gameService;
	
	@Resource(name=AccountService.ID_NAME)
	private AccountService accountService;
	
	private ActionForm form;
	private Account account;
	private List<Account> accountList;
	private List<AccountDetail> detailList;
	private AccountDetail accountDetail;
	private List<Game> gameList;
	
	private String result;
	
	@Action(value = "/manage/game/accountlist", results = { @Result(name = SUCCESS, location = "/manage/game/account_list.jsp")})
	public String toAccountList(){
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/game/listAccount", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String listAccount(){
		
		if(form == null){
			form = new ActionForm();
		}
		
		if(this.getiSortCol_0() != null && this.getiSortCol_0() > -1){
			form.setSortName(super.getStr("mDataProp_"+this.getiSortCol_0()));
			form.setSortType(this.getsSortDir_0());
		}
		accountList = accountService.listAccount(form, super.getReqPages());
		
		return SUCCESS;
	}
	
	@Action(value = "/manage/game/toEditAccount", results = { @Result(name = SUCCESS, location = "/manage/game/account_edit.jsp")})
	public String toEditAccount(){
		long id = super.getLongParamter("id", 0l);
		if(id > 0){
			account = accountService.find(Account.class, id);
		}
		if(form == null){
			form = new ActionForm();
		}
		gameList = gameService.listGame(form, null);
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/game/toAccountDetail", results = { @Result(name = SUCCESS, location = "/manage/game/account_detail.jsp")})
	public String toAccountDetail(){
		long id = super.getLongParamter("id", 0l);
		if(id > 0){
			account = accountService.find(Account.class, id);
			detailList = accountService.listDetail(id);
		}
		if(form == null){
			form = new ActionForm();
		}
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/game/saveAccount", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveAccount(){
		
		if(account != null){
			
			long gameId = super.getLongParamter("gameId", 0);
			
			Account bean = null;
			if(account.getId() != null && account.getId() > 0){
				bean = accountService.find(Account.class, account.getId());
			}else{
				bean = new Account();
			}
			
			bean.setGame(gameService.find(Game.class, gameId));
			bean.setUserTag(account.getUserTag());
			bean.setCurrIndex(account.getCurrIndex());
			bean.setRemark(account.getRemark());
			if(bean.getId() != null && bean.getId() > 0){
				accountService.update(bean);
			}else{
				//初始化20个账号明细
				detailList = new ArrayList<AccountDetail>();
				AccountDetail detail = null;
				for(int i = 0;i < 20;i++){
					detail = new AccountDetail();
					detail.setAccount(bean);
					detail.setAdindex(i+1);
					detailList.add(detail);
				}
				accountService.save(bean, detailList);
			}
			
			result = "保存成功！";
		}else{
			result = "不允许空值";
		}
		
		
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/game/toEditAccountDetail", results = { @Result(name = SUCCESS, location = "/manage/game/account_detail_edit.jsp")})
	public String toEditAccountDetail(){
		Long accountId = super.getLongParamter("accountId", 0);
		int adindex = super.getIntParamter("adindex", -1);
		
		accountDetail = accountService.getDetail(accountId, adindex);
		
		return SUCCESS;
	}
	

	@Action(value = "/manage/game/saveAccountDetail", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveAccountDetail(){
		
		Long accountId = super.getLongParamter("accountId", 0);
		int adindex = super.getIntParamter("adindex", -1);
		Long detailId = super.getLongParamter("detailId", 0);
		
		if(detailId > 0){
			accountDetail = accountService.find(AccountDetail.class, detailId);
		}else{
			accountDetail = new AccountDetail();
		}
		accountDetail.setAccount(accountService.find(Account.class, accountId));
		accountDetail.setAdindex(adindex);
		accountDetail.setContent(super.getStr("content"));
		
		if(accountDetail.getId()!=null && accountDetail.getId() > 0){
			accountService.update(accountDetail);
		}else{
			accountService.create(accountDetail);
		}
		
		result = "SUCCESS";
		
		return SUCCESS;
	}
	
	
	@Action(value = "/updateCurrIndex")
	public void updateCurrIndex(){
		String userTag = super.getStr("userTag");
		Long gameId = super.getLongParamter("gameId", 0);
		
		
		try {
			account = accountService.getAccount(gameId,userTag);
			if(account != null){
				account.setCurrIndex(super.getIntParamter("currIndex", 1));
				accountService.update(account);
			}
			response.getWriter().print("success");
		} catch (IOException e) {
			e.printStackTrace();
			try {
				response.getWriter().print("error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	@Action(value = "/getCurrIndex")
	public void getCurrIndex(){
		String userTag = super.getStr("userTag");
		Long gameId = super.getLongParamter("gameId", 0);
		
		account = accountService.getAccount(gameId,userTag);
		
		try {
			response.getWriter().print(account.getCurrIndex());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Action(value = "/getAccounts")
	public void getAccounts(){
		String userTag = super.getStr("userTag");
		Long gameId = super.getLongParamter("gameId", 0);
		int adindex = super.getIntParamter("adindex", -1);
		response.setHeader("Content-type", "text/plain;charset=UTF-8");  
		account = accountService.getAccount(gameId,userTag);
		AccountDetail detail = accountService.getDetail(account.getId(), adindex);
		try {
			response.getWriter().print(detail!=null?detail.getContent():"");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public GameService getGameService() {
		return gameService;
	}


	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}


	public AccountService getAccountService() {
		return accountService;
	}


	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}


	public ActionForm getForm() {
		return form;
	}


	public void setForm(ActionForm form) {
		this.form = form;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	public List<Account> getAccountList() {
		return accountList;
	}


	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}


	public List<AccountDetail> getDetailList() {
		return detailList;
	}


	public void setDetailList(List<AccountDetail> detailList) {
		this.detailList = detailList;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public List<Game> getGameList() {
		return gameList;
	}


	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}


	public AccountDetail getAccountDetail() {
		return accountDetail;
	}


	public void setAccountDetail(AccountDetail accountDetail) {
		this.accountDetail = accountDetail;
	}
	

	
	
	
	
	
	

}
