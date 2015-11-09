package com.zhc.ask.action.form;

public class ActionForm {
	
	private Long id;
	
	private String sortName;//排序字段
	
	private String sortType;//排序类型
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}


}
