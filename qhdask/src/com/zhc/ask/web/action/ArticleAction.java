package com.zhc.ask.web.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.article.entity.Article;
import com.zhc.article.service.ArticleService;
import com.zhc.sys.action.BaseAction;

@Controller
@ParentPackage("json-default") 
public class ArticleAction extends BaseAction {
	
	@Resource(name = ArticleService.ID_NAME)
	private ArticleService articleService;
	
	private Article abean;
	
	@Action(value = "/article", results = { @Result(name = SUCCESS, location = "/article.jsp") })
	public String article() {
		
		long id = super.getLongParamter("id", 0);
		if(id > 0){
			abean = articleService.find(Article.class, id);
		}
		return SUCCESS;
	}
	
	public Article getAbean() {
		return abean;
	}


	public void setAbean(Article abean) {
		this.abean = abean;
	}

	

}
