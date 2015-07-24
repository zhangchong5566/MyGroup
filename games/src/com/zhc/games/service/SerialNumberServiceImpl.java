package com.zhc.games.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.zhc.games.action.form.ActionForm;
import com.zhc.games.entity.SerialNumber;
import com.zhc.sys.service.base.BaseJpaService;
import com.zhc.sys.service.base.Pages;

@Transactional
public class SerialNumberServiceImpl extends BaseJpaService  implements SerialNumberService {

	@Override
	public SerialNumber getByNumber(String snumber) {
		if(StringUtils.isNotBlank(snumber)){
			return super.getSingleResult(SerialNumber.class, "serialNumber=?", new Object[]{snumber.trim()});
		}
		return null;
	}

	@Override
	public List<SerialNumber> listSN(ActionForm form, Pages pages) {
		StringBuffer jpql = new StringBuffer();
		List params = new ArrayList<>();
		if(StringUtils.isNotBlank(form.getSerialNumber())){
			jpql.append(" and serialNumber like ?");
			params.add("%"+form.getSerialNumber()+"%");
		}
		
		jpql.insert(0,"from SerialNumber where 1=1");
		
		if (StringUtils.isNotBlank(form.getSortName())) {
			jpql.append(" order by " + form.getSortName() + " "
					+ form.getSortType());
		}
		return super.queryByJPQL("select count(id) "+jpql, jpql.toString(), params.toArray(), pages);
	}

	
	
	
}
