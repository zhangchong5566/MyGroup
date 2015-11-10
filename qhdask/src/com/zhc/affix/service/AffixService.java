package com.zhc.affix.service;

import java.util.List;

import com.zhc.affix.entity.Affix;
import com.zhc.sys.service.base.BaseJpaServiceInterf;

public interface AffixService  extends BaseJpaServiceInterf {
	
	public static final String ID_NAME = "affixService";
	
	/**
	 * 查询附件
	 * @param objectType
	 * @param objectId
	 * @return
	 */
	public abstract List<Affix> getAffixs(long objectType, String objectId);
	
	/**
	 * 
	 * @param objectTypes
	 * @param objectIds
	 * @return
	 */
	public abstract List<Affix> getAffixs(String objectTypes, String objectIds);
	
	/**
	 * 更新附件id
	 * @param objectType
	 * @param oldObjId
	 * @param newObjId
	 */
	public int updateAffixId(long objectType,String oldObjId,String newObjId );
	

	
	/**
	 * 更新附件点击率
	 * @param id
	 */
	public void updateAffixHotAdd(long id );
	
	/**
	 * 删除指定对象的附件
	 * @author zhangchong
	 * 2011-8-16
	 * @description 
	 * @param type
	 * @param objId
	 */
	public void deleteByTypeAndOBjId(Integer type,String objId);
	
	public long findMaxId();
	
	
	/**
	 * 删除垃圾文件
	 * @return
	 */
	public String deleteRubbishFile();
	
	public Affix findBySource(String source);

}
