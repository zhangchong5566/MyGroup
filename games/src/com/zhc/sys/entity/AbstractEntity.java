package com.zhc.sys.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AbstractEntity
 *
 */
@MappedSuperclass

public class AbstractEntity implements java.io.Serializable{

	private static final long serialVersionUID = -7871895399607814958L;

	public AbstractEntity() {
		super();
	}
   
}
