package com.zhc.sys.service;

import java.util.List;

import com.zhc.sys.entity.SysPopedom;
import com.zhc.sys.service.base.BaseJpaServiceInterf;
import com.zhc.sys.service.base.Pages;

public interface SysPopedomService  extends BaseJpaServiceInterf{
	
	public static final String ID_NAME = "sysPopedomService";
	
	public List<SysPopedom> listPopedom(SysPopedom bean,Pages pages);

}
