package com.zhc.sys.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

@Entity
@Table(name="syspopedom")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class SysPopedom extends AbstractEntityGenerate implements Serializable {
	private String code;
	private String description;
	private Set<SysRole> sysRoles;

	public SysPopedom() {
	}


	@Column(name="sys_code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@ManyToMany(mappedBy="sysPopedoms")
	@JSON(serialize=false)
	public Set<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
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
		SysPopedom other = (SysPopedom) obj;
		if (this.getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!this.getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	public SysPopedom(Long id, String code, String description) {
		this.setId(id);
		this.code = code;
		this.description = description;
	}
}