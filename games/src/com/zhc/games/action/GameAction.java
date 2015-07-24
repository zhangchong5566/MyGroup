package com.zhc.games.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.games.action.form.ActionForm;
import com.zhc.games.entity.Game;
import com.zhc.games.entity.SerialNumber;
import com.zhc.games.service.GameService;
import com.zhc.games.service.SerialNumberService;
import com.zhc.sys.action.BaseAction;
import com.zhc.util.DateUtil;
@Controller
@ParentPackage("json-default")  
public class GameAction extends BaseAction {
	
	@Resource(name=GameService.ID_NAME)
	private GameService gameService;
	
	@Resource(name=SerialNumberService.ID_NAME)
	private SerialNumberService snService;
	
	private ActionForm form;
	private Game game;
	private SerialNumber sn;
	private List<Game> gameList;
	private List<SerialNumber> snList;
	private String result;
	
	@Action(value = "/manage/game/gamelist", results = { @Result(name = SUCCESS, location = "/manage/game/game_list.jsp")})
	public String toGameList(){
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/game/listGame", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String listGame(){
		
		if(form == null){
			form = new ActionForm();
		}
		
		if(this.getiSortCol_0() != null && this.getiSortCol_0() > -1){
			form.setSortName(super.getStr("mDataProp_"+this.getiSortCol_0()));
			form.setSortType(this.getsSortDir_0());
		}
		gameList = gameService.listGame(form, super.getReqPages());
		
		return SUCCESS;
	}
	
	@Action(value = "/manage/game/saveGame", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveGame(){
		
		if(game != null){
			Game bean = null;
			if(game.getId() != null && game.getId() > 0){
				bean = gameService.find(Game.class, game.getId());
			}else{
				bean = new Game();
				bean.setCreateDate(new Date());
			}
			bean.setGname(game.getGname());
			bean.setDescription(game.getDescription());
			
			if(bean.getId() != null && bean.getId() > 0){
				gameService.update(bean);
			}else{
				gameService.create(bean);
			}
			result = "保存成功！";
		}else{
			result = "不允许空值";
		}
		
		
		return SUCCESS;
	}
	

	@Action(value = "/manage/game/toEditGame", results = { @Result(name = SUCCESS, location = "/manage/game/game_edit.jsp")})
	public String toEditGame(){
		long id = super.getLongParamter("id", 0l);
		if(id > 0){
			game = gameService.find(Game.class, id);
		}
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/game/toSerialNumberList", results = { @Result(name = SUCCESS, location = "/manage/game/sn_list.jsp")})
	public String toSerialNumberList(){
		return SUCCESS;
	}
	
	@Action(value = "/manage/game/listSerialNumber", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String listSerialNumber(){
		
		if(form == null){
			form = new ActionForm();
		}
		
		if(this.getiSortCol_0() != null && this.getiSortCol_0() > -1){
			form.setSortName(super.getStr("mDataProp_"+this.getiSortCol_0()));
			form.setSortType(this.getsSortDir_0());
		}
		snList = snService.listSN(form, super.getReqPages());
		
		return SUCCESS;
	}
	
	@Action(value = "/manage/game/toEditSN", results = { @Result(name = SUCCESS, location = "/manage/game/sn_edit.jsp")})
	public String toEditSN(){
		long id = super.getLongParamter("id", 0l);
		if(id > 0){
			sn = snService.find(SerialNumber.class, id);
		}
		form = new ActionForm();
		form.setSortName("id");
		form.setSortType("desc");
		gameList = gameService.listGame(form, null);
		return SUCCESS;
	}
	
	@Action(value = "/manage/game/saveSN", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveSN(){
		
		if(sn != null){
			
			long gameId = super.getLongParamter("gameId", 0);
			String beginDate = super.getStr("beginDate");
			String endDate = super.getStr("endDate");
			
			SerialNumber bean = null;
			if(sn.getId() != null && sn.getId() > 0){
				bean = snService.find(SerialNumber.class, sn.getId());
			}else{
				bean = new SerialNumber();
				bean.setCreateDate(new Date());
			}
			bean.setSerialNumber(sn.getSerialNumber());
			bean.setGame(gameService.find(Game.class, gameId));
			try {
				bean.setBeginDate(DateUtil.parseToDate(beginDate, "yyyy-MM-dd HH:mm:ss"));
				bean.setEndDate(DateUtil.parseToDate(endDate, "yyyy-MM-dd HH:mm:ss"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(bean.getId() != null && bean.getId() > 0){
				snService.update(bean);
			}else{
				snService.create(bean);
			}
			result = "保存成功！";
		}else{
			result = "不允许空值";
		}
		
		
		return SUCCESS;
	}
	
	@Action(value = "/checkSN")
	public void checkSN(){
		String snStr = super.getStr("s");
		result = "0";
		if(StringUtils.isNotBlank(snStr)){
			sn = snService.getByNumber(snStr);
			if(sn!=null){
				Date now = DateUtil.getFormatedDateString(8);//获取北京时间
				Date beginDate = sn.getBeginDate();
				Date endDate = sn.getEndDate();
				if(beginDate.getTime()<=now.getTime() && endDate.getTime()>=now.getTime()){
					result = "1";//在有效期内
				}
				
			}
		}
		try {
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	


	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}


	public ActionForm getForm() {
		return form;
	}


	public void setForm(ActionForm form) {
		this.form = form;
	}


	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public List<SerialNumber> getSnList() {
		return snList;
	}


	public void setSnList(List<SerialNumber> snList) {
		this.snList = snList;
	}


	public SerialNumber getSn() {
		return sn;
	}


	public void setSn(SerialNumber sn) {
		this.sn = sn;
	}
	
	
	
	
	
	
	

}
