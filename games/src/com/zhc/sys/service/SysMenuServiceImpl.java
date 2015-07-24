package com.zhc.sys.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.zhc.sys.entity.SysMenu;
import com.zhc.sys.service.base.BaseJpaService;

public class SysMenuServiceImpl extends BaseJpaService implements
		SysMenuService {

	@Override
	public List<SysMenu> getTree(long rootId) {

		SysMenu rootObj = (SysMenu) super.find(SysMenu.class,
				Long.valueOf(rootId));
		List<SysMenu> tree = new ArrayList();

		loadTreeChilds(rootObj, tree, 1);

		return tree;
	}

	@Override
	public List<SysMenu> getTree(long rootId, Set<Long> menuIds) {

		SysMenu rootObj = (SysMenu) super.find(SysMenu.class,
				Long.valueOf(rootId));
		List<SysMenu> tree = new ArrayList();

		loadTreeChilds(rootObj, tree, 1, menuIds);

		return tree;

	}

	private void loadTreeChilds(SysMenu d, List<SysMenu> tree, int level) {
		tree.add(d);
		d.setLevel(Integer.valueOf(level));
		if (d.getChilds() == null) {
			return;
		}
		Iterator<SysMenu> childs = d.getChilds().iterator();
		while (childs.hasNext()) {
			SysMenu child = (SysMenu) childs.next();
			loadTreeChilds(child, tree, level + 1);
		}
	}

	private void loadTreeChilds(SysMenu d, List<SysMenu> tree, int level,
			Set<Long> menuIds) {

		if (!menuIds.contains(d.getId())) {
			return;
		}
		tree.add(d);
		d.setLevel(Integer.valueOf(level));
		if (d.getChilds() == null) {
			return;
		}

		Iterator<SysMenu> childs = d.getChilds().iterator();
		while (childs.hasNext()) {
			SysMenu child = (SysMenu) childs.next();
			loadTreeChilds(child, tree, level + 1, menuIds);
		}
	}

	@Override
	public List<SysMenu> getMenusByParentId(long parentId) {

		return super
				.queryByJPQL(" select new SysMenu(d.id,d.name,d.description,d.orderBy,d.img,d.link,d.alias) from SysMenu d where d.parentObj.id= "
						+ parentId + " order by d.orderby ");
	}

}
