package com.zhc.games.service;

import java.util.List;

import com.zhc.games.action.form.ActionForm;
import com.zhc.games.entity.SerialNumber;
import com.zhc.sys.service.base.BaseJpaServiceInterf;
import com.zhc.sys.service.base.Pages;

public interface SerialNumberService  extends BaseJpaServiceInterf{

	public static final String ID_NAME = "serialNumberService";
	
	public SerialNumber getByNumber(String snumber);
	
	public List<SerialNumber> listSN(ActionForm form,Pages pages);
	
}
