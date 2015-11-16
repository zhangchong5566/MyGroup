package com.zhc.ask.service;

import java.util.List;
import java.util.Set;

import com.zhc.ask.entity.AskClassify;
import com.zhc.sys.service.base.BaseJpaServiceInterf;

public interface AskClassifyService extends BaseJpaServiceInterf {

	public static final String ID_NAME = "askClassifyService";

	public abstract List<AskClassify> getTree(long rootId);

	public abstract List<AskClassify> getTree(long rootId, Set<Long> classifyIds);

	public abstract List<AskClassify> getClassifysByParentId(long id);
	
	

}
