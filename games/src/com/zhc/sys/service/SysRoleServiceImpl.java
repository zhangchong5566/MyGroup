package com.zhc.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.zhc.sys.entity.SysRole;
import com.zhc.sys.entity.SysRoleToSysMenu;
import com.zhc.sys.service.base.BaseJpaService;
import com.zhc.sys.service.base.Pages;

public class SysRoleServiceImpl extends BaseJpaService implements SysRoleService {

	@Override
	public List<SysRole> listRole(SysRole bean, Pages pages) {
		
		String jpql = "from SysRole where 1=1";
		
		List params = new ArrayList();
		
		if(bean != null){
			if(StringUtils.isNotBlank(bean.getDescription())){
				jpql += " and description=?";
				params.add(bean.getDescription());
			}
		}
		
		String countJpql = "select count(id) "+jpql;
		String queryJpql = "select new SysRole(id,name,description) "+jpql;
		
		if(pages != null){
			return super.queryByJPQL(countJpql, queryJpql, params.toArray(), pages);
		}else{
			return super.queryByJPQL(queryJpql, params.toArray());
		}
	}

	@Override
	public void saveRoleToMenu(SysRole sysrole, List<SysRoleToSysMenu> rmList) {
		
		String jpql = "delete SysRoleToSysMenu where id.sysrole.id="+sysrole.getId();
		
		super.executeJPQL(jpql);
		
		if(rmList != null){
			for(SysRoleToSysMenu rm : rmList){
				super.getEntityManager().persist(rm);
			}
		}
		
	}

	

}
