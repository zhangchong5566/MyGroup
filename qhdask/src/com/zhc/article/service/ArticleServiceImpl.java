package com.zhc.article.service;

import java.util.Date;
import java.util.List;

import com.zhc.article.entity.Article;
import com.zhc.sys.service.base.BaseJpaService;
import com.zhc.sys.service.base.Pages;

public class ArticleServiceImpl  extends BaseJpaService implements ArticleService {

	@Override
	public List<Article> listArticles(Pages pages) {
		
		String jpql = "from Article";
		
		String select = "select new Article(id,atitle, asubtitle, aauthor, asource, aputouttime, awritetime) ";
		
		return super.queryByJPQL("select count(*) "+jpql, select+jpql, pages);
	}

	

}
