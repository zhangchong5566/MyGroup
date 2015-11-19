package com.zhc.article.action;

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
	
	private List<Article> artList;
	
	private Article abean;
	
	private String editorValue;
	
	private String message;
	
	@Action(value = "/manage/article/toList", results = { @Result(name = SUCCESS, location = "/manage/article/art_list.jsp") })
	public String toNomalMemberList() {
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/article/listArticle", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String listArticle(){
		
		artList = articleService.listArticles(super.getReqPages());
		
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/article/toEditArticle", results = { @Result(name = SUCCESS, location = "/manage/article/art_edit.jsp") })
	public String toEditArticle() {
		long id = super.getLongParamter("id", 0);
		if(id > 0){
			abean = articleService.find(Article.class, id);
		}
		
		return SUCCESS;
	}
	
	
	@Action(value = "/manage/article/saveArticle", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String saveArticle(){
		
		if(abean != null){
			
			//String editorValue = super.getStr("editorValue");
			
			Article article = null;
			Date now = new Date();
			if(abean.getId() != null && abean.getId() > 0){
				article = articleService.find(Article.class, abean.getId());
			}else{
				article = new Article();
				article.setAwritetime(now);
			}
			article.setAauthor(abean.getAauthor());
			article.setAcontent(editorValue);
			article.setAputouttime(now);
			article.setAsource(abean.getAsource());
			article.setAsubtitle(abean.getAsubtitle());
			article.setAtitle(abean.getAtitle());
			article.setAputouttime(now);
			
			if(article.getId() !=null && article.getId() > 0){
				articleService.update(article);
			}else{
				articleService.create(article);
			}
			
			message = "Success";
		}
		
		return SUCCESS;
	}
	
	@Action(value = "/manage/article/delArticle", results = { @Result(name = SUCCESS, type="json",params={"ignoreHierarchy","false"})})
	public String delArticle(){
		
		long id = super.getLongParamter("id", 0);
		articleService.delete(Article.class, id);
		message = "Success";
		
		return SUCCESS;
	}

	public List<Article> getArtList() {
		return artList;
	}

	public void setArtList(List<Article> artList) {
		this.artList = artList;
	}


	public Article getAbean() {
		return abean;
	}


	public void setAbean(Article abean) {
		this.abean = abean;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getEditorValue() {
		return editorValue;
	}


	public void setEditorValue(String editorValue) {
		this.editorValue = editorValue;
	}
	
	

}
