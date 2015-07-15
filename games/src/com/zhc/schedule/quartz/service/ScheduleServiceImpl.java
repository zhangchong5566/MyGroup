package com.zhc.schedule.quartz.service;

import java.util.List;
import java.util.Map;

import com.zhc.schedule.quartz.entity.QzJobDetail;
import com.zhc.schedule.quartz.entity.QzTrigger;
import com.zhc.sys.service.base.BaseJpaService;
import com.zhc.sys.service.base.Pages;

public class ScheduleServiceImpl extends BaseJpaService implements ScheduleService {

	@Override
	public List<QzTrigger> getNormalQzTriggers() {
		
		return super.queryByJPQL(" from QzTrigger q left join fetch q.qzJobDetail  where q.qzJobDetail.id<>4  and q.status=1 order by q.id desc ");
		
	}

	@Override
	public QzTrigger getQzTrigger(long id) {
		
		return (QzTrigger)super.find(QzTrigger.class, Long.valueOf(id));
		
	}

	@Override
	public List<Map> getVirtualTriggers(Pages pages) {
		
		String sql = "select q.id,q.name,q.startDate,q.endDate,q.extendf9 from qz_trigger q where q.qzJobDetail_id in (4,5,6,7) order by q.id desc";
		
		return super.queryBySQL2("select count(t.id) from ("+sql+") t", sql, pages);
	}

	@Override
	public List<QzJobDetail> listJob(Pages pages) {
		if(pages != null){
			return super.getScrollData(QzJobDetail.class, pages, null, null);
		}else{
			return super.getScrollData(QzJobDetail.class);
		}
	}

	@Override
	public List<QzTrigger> listTrigger(Pages pages) {
		return super.getScrollData(QzTrigger.class, pages, null, null);
	}
	

}
