package com.zhc.sys.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

@Entity
@Table(name="sysrole")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class SysRole  extends AbstractEntityGenerate implements Serializable {
	private String name;
	private Set<SysPopedom> sysPopedoms;
	private String description;
	private String popedoms;
	private Integer type;
	private String extendf1;
	private String extendf2;
	private Set<SysRoleToSysMenu> sysMenus;

	public SysRole() {
	}

	public SysRole(Long id) {
		this.setId(id);
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="sysrole_syspopedom",joinColumns=@JoinColumn(name="sysrole_id"),
    inverseJoinColumns=@JoinColumn(name="syspopedom_id"))
	@JSON(serialize=false)
	public Set<SysPopedom> getSysPopedoms() {
		return this.sysPopedoms;
	}

	public void setSysPopedoms(Set<SysPopedom> sysPopedoms) {
		this.sysPopedoms = sysPopedoms;
	}

	

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + (this.getId() == null ? 0 : this.getId().hashCode());
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
		SysRole other = (SysRole) obj;
		if (this.getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!this.getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	public SysRole(Long id, String name, String description) {
		this.setId(id);
		this.name = name;
		this.description = description;
		this.popedoms = "";
		StringBuffer sb = new StringBuffer();
		if (getSysPopedoms() != null) {
			for (SysPopedom sysPopedom : getSysPopedoms()) {
				if (sb.length() > 0) {
					sb.append("|");
				}
				sb.append(sysPopedom.getDescription());
			}
			this.popedoms = sb.toString();
		}
	}

	public String getPopedoms() {
		return this.popedoms;
	}

	public void setPopedoms(String popedoms) {
		this.popedoms = popedoms;
	}

	@OneToMany(mappedBy="id.sysrole")
	@JSON(serialize=false)
	public Set<SysRoleToSysMenu> getSysMenus() {
		return this.sysMenus;
	}

	public void setSysMenus(Set<SysRoleToSysMenu> sysMenus) {
		this.sysMenus = sysMenus;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SysRole(Long id, String name, String description, Integer type,
			String extendf1, String extendf2) {
		this.setId(id);
		this.name = name;
		this.description = description;
		this.type = type;
		this.extendf1 = extendf1;
		this.extendf2 = extendf2;
	}
}