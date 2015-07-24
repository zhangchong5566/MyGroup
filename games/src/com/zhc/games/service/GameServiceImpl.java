package com.zhc.games.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.zhc.games.action.form.ActionForm;
import com.zhc.games.entity.Game;
import com.zhc.sys.service.base.BaseJpaService;
import com.zhc.sys.service.base.Pages;
@Transactional
public class GameServiceImpl extends BaseJpaService  implements GameService {

	@Override
	public List<Game> listGame(ActionForm form, Pages pages) {
		
		StringBuffer jpql = new StringBuffer();
		List params = new ArrayList<>();
		if(StringUtils.isNotBlank(form.getGameName())){
			jpql.append(" and gameName like ?");
			params.add("%"+form.getGameName()+"%");
		}
		
		jpql.insert(0,"from Game where 1=1");
		
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

	
	
	
	
}
