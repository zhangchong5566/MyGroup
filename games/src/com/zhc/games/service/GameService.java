package com.zhc.games.service;

import java.util.List;

import com.zhc.games.action.form.ActionForm;
import com.zhc.games.entity.Game;
import com.zhc.sys.service.base.BaseJpaServiceInterf;
import com.zhc.sys.service.base.Pages;

public interface GameService  extends BaseJpaServiceInterf{
	
	public static final String ID_NAME = "gameService";
	
	public List<Game> listGame(ActionForm form,Pages pages);
	

}
