package com.zhc.sys.service.base;

import java.util.List;

import com.zhc.sys.entity.AbstractEntityGenerate;

public interface BaseJpaServiceInterf {
	
	public <T extends AbstractEntityGenerate> void create(T entity);
	
	public <T extends AbstractEntityGenerate> void createBatch(List<T> entitys);
	
	public <T extends AbstractEntityGenerate> void update(T entity);
	
	public <T extends AbstractEntityGenerate> void delete(Class<T> entityClass,
			Object entityid);
	
	public <T extends AbstractEntityGenerate> void delete(Class<T> entityClass,
			Object[] entityids);
	
	public <T extends AbstractEntityGenerate> T find(Class<T> entityClass,
			Object entityId);
	
	public int executeJPQL(String jpql);
	public int executeJPQL(String jpql,Object[] params);
	
	public int executeSQL(String sql);
	public int executeSQL(String sql,Object[] params);
	

}
