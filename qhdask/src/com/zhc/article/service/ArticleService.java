package com.zhc.article.service;

import java.util.List;

import com.zhc.article.entity.Article;
import com.zhc.sys.service.base.BaseJpaServiceInterf;
import com.zhc.sys.service.base.Pages;

public interface ArticleService extends BaseJpaServiceInterf {

	public static final String ID_NAME = "articleService";
	
	public List<Article> listArticles(Pages pages);
	
}
