package com.zhc.article.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

/**
 * 文章
* @ClassName: Article 
* @Description: TODO
* @author zhangchong
* @date 2015年11月18日 下午9:15:03 
*
 */
@Entity
@Table(name = "article")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class Article extends AbstractEntityGenerate implements Serializable{
	
	private String atitle;// 标题

	private String asubtitle;// 副标题
	
	private String aauthor;// 作者

	private String asource;// 来源

	private String acontent;// 内容

	private Date aputouttime;// 发布时间

	private Date awritetime;// 录入时间
	
	public Article(){}

	public Article(Long id,String atitle, String asubtitle, String aauthor, String asource, Date aputouttime, Date awritetime) {
		super();
		super.setId(id);
		this.atitle = atitle;
		this.asubtitle = asubtitle;
		this.aauthor = aauthor;
		this.asource = asource;
		this.aputouttime = aputouttime;
		this.awritetime = awritetime;
	}

	public String getAtitle() {
		return atitle;
	}

	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}

	public String getAsubtitle() {
		return asubtitle;
	}

	public void setAsubtitle(String asubtitle) {
		this.asubtitle = asubtitle;
	}

	public String getAauthor() {
		return aauthor;
	}

	public void setAauthor(String aauthor) {
		this.aauthor = aauthor;
	}

	public String getAsource() {
		return asource;
	}

	public void setAsource(String asource) {
		this.asource = asource;
	}

	@Column(columnDefinition="LONGTEXT")
	public String getAcontent() {
		return acontent;
	}

	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}

	public Date getAputouttime() {
		return aputouttime;
	}

	public void setAputouttime(Date aputouttime) {
		this.aputouttime = aputouttime;
	}

	public Date getAwritetime() {
		return awritetime;
	}

	public void setAwritetime(Date awritetime) {
		this.awritetime = awritetime;
	}
	
	

}
