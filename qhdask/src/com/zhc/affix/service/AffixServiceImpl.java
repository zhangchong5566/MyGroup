package com.zhc.affix.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhc.affix.entity.Affix;
import com.zhc.sys.service.base.BaseJpaService;
import com.zhc.util.BeanUtil;

public class AffixServiceImpl extends BaseJpaService implements AffixService {

	@Override
	public List<Affix> getAffixs(long objectType, String objectId) {
		if (objectType == -1) {
			return super.queryByJPQL(" from Affix a where a.objectId=? order by a.id desc ", new Object[] { objectId });
		}
		return super.queryByJPQL(" from Affix a where a.objectType= " + objectType + " and a.objectId=? order by a.id desc ", new Object[] { objectId });
	}

	@Override
	public List<Affix> getAffixs(String objectTypes, String objectIds) {
		return super.queryByJPQL(" from Affix a where a.objectType in(" + objectTypes + ") and a.objectId in(" + objectIds + ") order by a.id desc ");
	}

	@Override
	public int updateAffixId(long objectType, String oldObjId, String newObjId) {

		return super.executeJPQL("update Affix a set a.objectId=? where a.objectType= " + objectType + " and a.objectId=? ", new Object[] { newObjId, oldObjId });
	}

	@Override
	public void updateAffixHotAdd(long id) {
		super.executeJPQL(" update Affix a set a.hotNum=a.hotNum+1 where a.id=" + id);
	}

	@Override
	public void deleteByTypeAndOBjId(Integer type, String objId) {
		List<Affix> alist = this.getAffixs(type, objId);

		if (alist != null) {
			File file = null;
			for (Affix a : alist) {
				file = new File(a.getSource());
				boolean success = file.delete();
				System.out.println("删除文件：" + a.getSource() + "结果：" + success);
			}
		}

		super.executeJPQL("delete from Affix where objectType=? and objectId=?", new Object[] { type, objId });
	}

	@Override
	public long findMaxId() {
		String sql = "select max(id) as id from Affix a";

		List<Map> list = super.queryBySQL2(sql);

		if (list != null && list.size() > 0) {
			Map map = list.get(0);
			return (Long) map.get("id");
		}

		return 0;
	}

	@Override
	public String deleteRubbishFile() {
		// 第一种情况：ObjectId中包含-的数据，是未保存成功的，说明没有数据源关联，可以删除
		String jpql = "from Affix a where a.objectId like '%-%'";
		List<Affix> list = super.queryByJPQL(jpql);

		StringBuffer msg = new StringBuffer();
		File file = null;
		for (Affix a : list) {
			file = new File(a.getSource());
			boolean b = file.delete();
			msg.append("<br/>删除" + a.getSource() + ">>" + b);
			super.delete(Affix.class, a.getId());
		}

		// 第二种情况：硬盘上附件目录里的文件，一一与附件表里的记录对比，如果在附件表里不存在，那么可以删除
		// 这个删除关键是匹配方法，如果匹配错误，那么可能会造成误删除，所以必须谨慎
		AffixConfig config = (AffixConfig) BeanUtil.getInstance().getBean(AffixConfig.ID_NAME);
		String root = config.getBaseDir();
		File rootPathFile = new File(root);

		List<String> fileList = new ArrayList<String>();

		ergodic(rootPathFile, fileList);

		Affix bean = null;

		for (String path : fileList) {

			bean = findBySource(path);

			// 说明数据库里没有此记录，那么删除文件
			if (bean == null) {
				file = new File(path);
				if (!file.isDirectory()) {// 如果不是文件夹才删除
					boolean b = file.delete();
					msg.append("<br/>删除" + path + ">>" + b);
					System.out.println("删除" + path + ">>" + b);
				}
			}

		}

		return msg.toString();
	}
	
	private List<String> ergodic(File file,List<String> resultFileName){
        File[] files = file.listFiles();
        if(files==null)return resultFileName;// 判断目录下是不是空的
        for (File f : files) {
            if(f.isDirectory()){// 判断是否文件夹
                resultFileName.add(f.getPath());
                ergodic(f,resultFileName);// 调用自身,查找子目录
            }else
                resultFileName.add(f.getPath());
        }
        return resultFileName;
    }

	@Override
	public Affix findBySource(String source) {
		String jpql = "from Affix a where a.source=?";
		List<Affix> list = super.queryByJPQL(jpql, new Object[] { source.replaceAll("\\\\", "/") });

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

}
