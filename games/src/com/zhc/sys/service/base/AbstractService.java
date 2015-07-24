package com.zhc.sys.service.base;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public abstract class  AbstractService {
	private EntityManagerFactory emf;
	
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	protected EntityManager getEntityManager()
	{
		EntityManagerHolder holder =  (EntityManagerHolder) TransactionSynchronizationManager.getResource(emf);
		return holder.getEntityManager();
		
	}
   
}
