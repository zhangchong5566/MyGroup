package com.zhc.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.zhc.sys.entity.SysPopedom;
import com.zhc.sys.service.base.BaseJpaService;
import com.zhc.sys.service.base.Pages;

public class SysPopedomServiceImpl extends BaseJpaService implements SysPopedomService {

	@Override
	public List<SysPopedom> listPopedom(SysPopedom bean,Pages pages) {
		
		String jpql = "from SysPopedom where 1=1";
		List params = new ArrayList();
		
		if(bean != null){
			if(StringUtils.isNotBlank(bean.getDescription())){
				jpql += " and description like ?";
				params.add("%"+bean.getDescription()+"%");
			}
		}
		String countJpql = "select count(id) "+jpql;
		String selectjpql = "select new SysPopedom(id,code,description) "+jpql;
		return super.queryByJPQL(countJpql, selectjpql, params.toArray(), pages);
	}

	

}
 