package com.zhc.ask.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.zhc.ask.entity.AskClassify;
import com.zhc.sys.service.base.BaseJpaService;

public class AskClassifyServiceImpl extends BaseJpaService  implements AskClassifyService {

	@Override
	public List<AskClassify> getTree(long rootId) {

		AskClassify rootObj = (AskClassify) super.find(AskClassify.class,
				Long.valueOf(rootId));
		List<AskClassify> tree = new ArrayList();

		loadTreeChilds(rootObj, tree, 1);

		return tree;
	}

	@Override
	public List<AskClassify> getTree(long rootId, Set<Long> menuIds) {

		AskClassify rootObj = (AskClassify) super.find(AskClassify.class,
				Long.valueOf(rootId));
		List<AskClassify> tree = new ArrayList();

		loadTreeChilds(rootObj, tree, 1, menuIds);

		return tree;

	}

	private void loadTreeChilds(AskClassify d, List<AskClassify> tree, int level) {
		tree.add(d);
		d.setLevel(Integer.valueOf(level));
		if (d.getChilds() == null) {
			return;
		}
		Iterator<AskClassify> childs = d.getChilds().iterator();
		while (childs.hasNext()) {
			AskClassify child = (AskClassify) childs.next();
			loadTreeChilds(child, tree, level + 1);
		}
	}

	private void loadTreeChilds(AskClassify d, List<AskClassify> tree, int level,
			Set<Long> menuIds) {

		if (!menuIds.contains(d.getId())) {
			return;
		}
		tree.add(d);
		d.setLevel(Integer.valueOf(level));
		if (d.getChilds() == null) {
			return;
		}

		Iterator<AskClassify> childs = d.getChilds().iterator();
		while (childs.hasNext()) {
			AskClassify child = (AskClassify) childs.next();
			loadTreeChilds(child, tree, level + 1, menuIds);
		}
	}

	@Override
	public List<AskClassify> getClassifysByParentId(long parentId) {

		return super
				.queryByJPQL(" select new AskClassify(d.id,d.name,d.description,d.orderBy,d.img,d.link,d.alias) from AskClassify d where d.parentObj.id= "
						+ parentId + " order by d.orderby ");
	}

}
