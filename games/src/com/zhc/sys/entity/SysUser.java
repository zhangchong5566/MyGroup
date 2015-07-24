package com.zhc.sys.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

/**
 * 后台运营用户
* @ClassName: SysUser 
* @Description: TODO
* @author zhangchong
* @date 2014年7月24日 下午2:43:30 
*
 */
@Entity
@Table(name="sysuser")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class SysUser extends AbstractEntityGenerate{
	
	private String loginName;//登录名
	
	private String password;
	
	private String trueName;//姓名
	
	private String email;
	
	private String phone;//电话
	
	private Date createDate;//创建时间
	
	private Date lastLoginTime;//最后登录时间
	
	private Integer status;//空或0：正常，1-冻结
	
	
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	
	
}
