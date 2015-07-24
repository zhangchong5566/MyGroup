package com.zhc.sys.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Entity implementation class for Entity: AbstractEntityGenerate
 * 
 */
@MappedSuperclass
public class AbstractEntityGenerate extends AbstractEntity implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7423282548122401124L;

	public AbstractEntityGenerate() {

		super();
	}

	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
