package com.zhc.sys.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

@Entity
@Table(name = "sysmenu")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class SysMenu extends AbstractEntityGenerate implements Serializable {
	private String name;
	private String ename;
	private String description;
	private Long orderBy;
	private String img;
	private String link;
	private SysMenu parentObj;
	private Set<SysMenu> childs;
	private Date createDate;
	private Date updateDate;
	private String alias;
	private String extendf1;
	private String extendf2;
	private Integer level;

	@Transient
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@ManyToOne
	@JoinColumn(name = "parentId")
	public SysMenu getParentObj() {
		return this.parentObj;
	}

	public void setParentObj(SysMenu parentObj) {
		this.parentObj = parentObj;
	}

	@OneToMany(mappedBy = "parentObj")
	@OrderBy("orderBy ASC")
	public Set<SysMenu> getChilds() {
		return this.childs;
	}

	public void setChilds(Set<SysMenu> childs) {
		this.childs = childs;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public SysMenu() {
	}

	public SysMenu(Long id) {
		this.setId(id);
	}

	public SysMenu(Long id, String name, String description, Long orderBy,
			String img, String link, String alias) {
		this.setId(id);
		this.name = name;
		this.description = description;
		this.orderBy = orderBy;
		this.img = img;
		this.link = link;
		this.alias = alias;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result
				+ (this.getId() == null ? 0 : this.getId().hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SysMenu other = (SysMenu) obj;
		if (this.getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!this.getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getExtendf1() {
		return this.extendf1;
	}

	public void setExtendf1(String extendf1) {
		this.extendf1 = extendf1;
	}

	public String getExtendf2() {
		return this.extendf2;
	}

	public void setExtendf2(String extendf2) {
		this.extendf2 = extendf2;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}