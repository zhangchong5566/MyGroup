package com.zhc.ask.entity;

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
 * 
* @ClassName: AskMember 
* @Description: TODO
* @author zhangchong
* @date 2015年11月9日 下午8:22:35 
*
 */
@Entity
@Table(name = "ask_member")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class AskMember  extends AbstractEntityGenerate implements Serializable{

	private String loginName;
	
	private String password;
	
	private Integer role;//1，普通用户；2，专家
	
	private Integer status;//状态，1-待审核（普通用户待审核不能提问，专家待审核不能回答问题），2-已审核
	
	private String trueName;//姓名
	
	private String sex;//性别
	
	private Date birthdate;//出生年月
	
	private String company;//工作单位
	
	private String education;//学历
	
	private String school;//毕业院校
	
	private String jobTitle;//职称
	
	private String professional;//专业
	
	private String currProfessional;//现从事专业
	
	private String currYears;//从事年限
	
	private String address;//通讯地址
	
	private String email;
	
	private String telephone;//联系电话
	
	private String qqWeChart;//QQ、微信
	
	private String expretTitle;//专家称号
	
	private String personProfile;//个人简介
	
	private String expertiseArea;//专业领域
	
	private String servicefall;//服务情况
	
	private String photo;//个人照片
	
	private Date joinDate;//注册时间
	
	private Date lastLoginDate;//最后时间

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

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
	
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getCurrProfessional() {
		return currProfessional;
	}

	public void setCurrProfessional(String currProfessional) {
		this.currProfessional = currProfessional;
	}

	public String getCurrYears() {
		return currYears;
	}

	public void setCurrYears(String currYears) {
		this.currYears = currYears;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getQqWeChart() {
		return qqWeChart;
	}

	public void setQqWeChart(String qqWeChart) {
		this.qqWeChart = qqWeChart;
	}

	public String getExpretTitle() {
		return expretTitle;
	}

	public void setExpretTitle(String expretTitle) {
		this.expretTitle = expretTitle;
	}

	@Column(columnDefinition="TEXT")
	public String getPersonProfile() {
		return personProfile;
	}
	
	public void setPersonProfile(String personProfile) {
		this.personProfile = personProfile;
	}

	@Column(columnDefinition="TEXT")
	public String getExpertiseArea() {
		return expertiseArea;
	}

	public void setExpertiseArea(String expertiseArea) {
		this.expertiseArea = expertiseArea;
	}

	@Column(columnDefinition="TEXT")
	public String getServicefall() {
		return servicefall;
	}

	public void setServicefall(String servicefall) {
		this.servicefall = servicefall;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	
	
}
