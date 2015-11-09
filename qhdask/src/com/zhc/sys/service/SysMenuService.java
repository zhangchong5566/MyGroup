package com.zhc.sys.service;

import java.util.List;
import java.util.Set;

import com.zhc.sys.entity.SysMenu;
import com.zhc.sys.service.base.BaseJpaServiceInterf;

public interface SysMenuService extends BaseJpaServiceInterf {

	public static final String ID_NAME = "sysMenuService";

	public abstract List<SysMenu> getTree(long rootId);
	
	public abstract List<SysMenu> getTree(long rootId,Set<Long> menuIds); 

	public abstract List<SysMenu> getMenusByParentId(long id);
}
