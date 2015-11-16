package com.zhc.ask.web.action.form;

public class SearchForm {

	private Long clsid;// 按分类搜索

	private String key;// 按关键字搜索

	private Integer status;// 按解决状态搜搜
	
	private Integer isopen;//是否公开，0-all，1-公开，2-不公开
	
	private Long memberId;//提问人ID
	
	private Long answerMemberId;//回答人ID
	
	public Long getClsid() {
		return clsid;
	}

	public void setClsid(Long clsid) {
		this.clsid = clsid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsopen() {
		return isopen;
	}

	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getAnswerMemberId() {
		return answerMemberId;
	}

	public void setAnswerMemberId(Long answerMemberId) {
		this.answerMemberId = answerMemberId;
	}
	
	

}
