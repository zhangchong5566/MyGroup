package com.zhc.sys.service.base;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import com.zhc.sys.entity.AbstractEntityGenerate;

/**
 * 封装常用增删改查操作
 * 
 * @since 2.0 泛型T挪到方法声明出,声明对象时不需要指定泛型.
 */
@SuppressWarnings("unchecked")
@Transactional
public abstract class BaseJpaService extends AbstractService implements
		BaseJpaServiceInterf {

	private QLBuilder sqlBuilder = new QLBuilder();

	/**
	 * 清除一级缓存的数据
	 */
	public void clear() {
		getEntityManager().clear();
	}

	/**
	 * 新增实体
	 * 
	 * @param entity
	 *            实体
	 */
	@Transactional
	public <T extends AbstractEntityGenerate> void create(T entity) {
		getEntityManager().persist(entity);
	}

	/**
	 * 批量新增实体
	 * 
	 * @param entitys
	 *            实体列表
	 */
	public <T extends AbstractEntityGenerate> void createBatch(List<T> entitys) {
		for (T entity : entitys) {
			create(entity);
		}
	}

	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体
	 */
	@Transactional
	public <T extends AbstractEntityGenerate> void update(T entity) {
		getEntityManager().merge(entity);
	}

	@Transactional
	public <T extends AbstractEntityGenerate> void saveAll(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			T entity = entitys.get(i);
			save(entity);
		}
	}

	@Transactional
	public <T extends AbstractEntityGenerate> void save(T entity) {
		if (entity.getId() == null) {
			this.create(entity);
		} else {
			this.update(entity);
		}
	}

	/**
	 * 删除实体
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityid
	 *            实体id
	 */
	@Transactional
	public <T extends AbstractEntityGenerate> void delete(Class<T> entityClass,
			Object entityid) {
		delete(entityClass, new Object[] { entityid });
	}

	/**
	 * 删除实体
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityids
	 *            实体id数组
	 */
	@Transactional
	public <T extends AbstractEntityGenerate> void delete(Class<T> entityClass,
			Object[] entityids) {

		for (Object id : entityids) {
			getEntityManager().remove(getEntityManager().find(entityClass, id));
		}
	}

	/**
	 * 根据条件删除
	 * 
	 * @author slx
	 * @date 2009-11-24 下午05:52:04
	 * @modifyNote
	 * @param entityClass
	 * @param where
	 * @param delParams
	 */
	@Transactional
	public <T extends AbstractEntityGenerate> void deleteByWhere(
			Class<T> entityClass, String where, Object[] delParams) {
		StringBuilder sf_QL = new StringBuilder("DELETE FROM ").append(
				sqlBuilder.getEntityName(entityClass)).append(" o WHERE 1=1 ");
		if (where != null && where.length() != 0) {
			sf_QL.append(" AND ").append(where);
		}
		Query query = getEntityManager().createQuery(sf_QL.toString());
		this.setQueryParams(query, delParams);

		query.executeUpdate();
	}

	/**
	 * 根据主键获取实体
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param entityId
	 *            实体id
	 * @return
	 */
	public <T extends AbstractEntityGenerate> T find(Class<T> entityClass,
			Object entityId) {
		return getEntityManager().find(entityClass, entityId);
	}

	public <T extends AbstractEntityGenerate> long getCount(Class<T> entityClass) {
		return getCountByWhere(entityClass, null, null);
	}

	/**
	 * 根据查询条件获取记录数量
	 * 
	 * @Title: getCountByWhere
	 * @Description: TODO
	 * @param @param entityClass
	 * @param @param whereql
	 * @param @param queryParams
	 * @param @return
	 * @return long
	 * @throws
	 * @date 2014年6月18日 上午9:50:13
	 */
	public <T extends AbstractEntityGenerate> long getCountByWhere(
			Class<T> entityClass, String whereql, Object[] queryParams) {
		StringBuilder sf_QL = new StringBuilder("SELECT COUNT(")
				.append(sqlBuilder.getPkField(entityClass, "o"))
				.append(") FROM ")
				.append(sqlBuilder.getEntityName(entityClass))
				.append(" o WHERE 1=1 ");
		if (StringUtils.isNotBlank(whereql)
				&& !whereql.trim().toUpperCase().startsWith("AND")) {
			sf_QL.append(" AND ").append(whereql);
		} else if (StringUtils.isNotBlank(whereql)) {
			sf_QL.append(whereql);
		}
		Query query = getEntityManager().createQuery(sf_QL.toString());
		this.setQueryParams(query, queryParams);
		return (Long) query.getSingleResult();
	}

	public <T extends AbstractEntityGenerate> boolean isExistedByWhere(
			Class<T> entityClass, String whereql, Object[] queryParams) {
		long count = getCountByWhere(entityClass, whereql, queryParams);
		return count > 0 ? true : false;
	}

	private <T extends AbstractEntityGenerate> List<T> getScrollData(
			Class<T> entityClass, int firstindex, int maxresult,
			String wherejpql, Object queryParams,
			LinkedHashMap<String, String> orderby) {

		String entityname = sqlBuilder.getEntityName(entityClass);
		Query query = getEntityManager()
				.createQuery(
						"SELECT o FROM "
								+ entityname
								+ " o "
								+ (StringUtils.isEmpty(wherejpql) ? ""
										: "WHERE " + wherejpql)
								+ sqlBuilder.buildOrderby(orderby));
		setQueryParams(query, queryParams);
		if (firstindex != -1 && maxresult != -1)
			query.setFirstResult(firstindex).setMaxResults(maxresult)
					.setHint("org.hibernate.cacheable", true);
		List<T> list = query.getResultList();
		return list;
	}

	public <T extends AbstractEntityGenerate> List<T> getScrollData(
			Class<T> entityClass, Pages pages, String wherejpql,
			Object[] queryParams, LinkedHashMap<String, String> orderby) {

		if (pages.getReCount() <= 0) {
			long count = getCountByWhere(entityClass, wherejpql, queryParams);

			pages.setReCount(count);
			pages.account();
		}
		if (pages.getReCount() == 0) {
			return new ArrayList();
		}

		int firstindex = (pages.getPage() - 1) * pages.getPageSize();
		int maxresult = pages.getPageSize();

		return getScrollData(entityClass, firstindex, maxresult, wherejpql,
				queryParams, orderby);
	}

	public <T extends AbstractEntityGenerate> List<T> getScrollData(
			Class<T> entityClass, Pages pages, String wherejpql,
			Object[] queryParams) {

		return getScrollData(entityClass, pages, wherejpql, queryParams, null);
	}

	public <T extends AbstractEntityGenerate> List<T> getScrollData(
			Class<T> entityClass) {
		return getScrollData(entityClass, -1, -1, null, null, null);
	}

	public <T extends AbstractEntityGenerate> List<T> getScrollData(
			Class<T> entityClass, String wherejpql, Object[] queryParams) {
		return getScrollData(entityClass, -1, -1, wherejpql, queryParams, null);
	}

	public <T extends AbstractEntityGenerate> List<T> getScrollData(
			Class<T> entityClass, String wherejpql, Map queryParams) {
		return getScrollData(entityClass, -1, -1, wherejpql, queryParams, null);
	}

	public <T extends AbstractEntityGenerate> List<T> getScrollData(
			Class<T> entityClass, String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(entityClass, -1, -1, wherejpql, queryParams,
				orderby);
	}

	public <T extends AbstractEntityGenerate> List<Object[]> queryFieldValues(
			Class<T> entityClass, String[] queryfields, String wherejpql,
			Object[] queryParams, int firstindex, int maxresult) {
		String entityname = sqlBuilder.getEntityName(entityClass);
		Query query = getEntityManager().createQuery(
				sqlBuilder.buildSelect(queryfields, "o") + " FROM "
						+ entityname + " o "
						+ (wherejpql == null ? "" : "WHERE " + wherejpql));
		setQueryParams(query, queryParams);
		if (firstindex >= 0) {
			query.setFirstResult(firstindex);
		}
		if (maxresult > 0) {
			query.setMaxResults(maxresult);
		}

		return query.getResultList();
	}

	public <T extends AbstractEntityGenerate> List<Object[]> queryFieldValues(
			Class<T> entityClass, String[] queryfields, String wherejpql,
			Object[] queryParams, Pages pages) {

		if (pages.getReCount() <= 0) {
			long count = getCountByWhere(entityClass, wherejpql, queryParams);

			pages.setReCount(count);
			pages.account();
		}
		if (pages.getReCount() == 0) {
			return new ArrayList(0);
		}

		int firstindex = (pages.getPage() - 1) * pages.getPageSize();
		int maxresult = pages.getPageSize();

		return queryFieldValues(entityClass, queryfields, wherejpql,
				queryParams, firstindex, maxresult);
	}

	public <T extends AbstractEntityGenerate> List<Object[]> queryFieldValues(
			Class<T> entityClass, String[] queryfields, String wherejpql,
			Object[] queryParams) {
		return queryFieldValues(entityClass, queryfields, wherejpql,
				queryParams, -1, -1);
	}

	public <T extends AbstractEntityGenerate> List<Object[]> queryFieldValues(
			Class<T> entityClass, String[] queryfields) {
		return queryFieldValues(entityClass, queryfields, null, null, -1, -1);
	}

	/**
	 * 根据SQL查询结果
	 * 
	 * @Title: queryBySQL
	 * @Description: TODO
	 * @param @param sql
	 * @param @param firstindex
	 * @param @param maxresult
	 * @param @return
	 * @return List<Object[]>
	 * @throws
	 * @date 2014年6月18日 上午11:21:05
	 */
	private <T extends AbstractEntityGenerate> List<Object[]> queryBySQL(
			String sql, Object[] queryParams, int firstindex, int maxresult) {

		Query query = getEntityManager().createNativeQuery(sql);
		setQueryParams(query, queryParams);
		if (firstindex >= 0) {
			query.setFirstResult(firstindex);
		}
		if (maxresult > 0) {
			query.setMaxResults(maxresult);
		}

		return query.getResultList();
	}

	/**
	 * 根据SQL查询结果
	 * 
	 * @Title: queryBySQL
	 * @Description: TODO
	 * @param @param sql
	 * @param @param firstindex
	 * @param @param maxresult
	 * @param @return
	 * @return List<Map>
	 * @throws
	 * @date 2014年6月18日 上午11:21:05
	 */
	private <T extends AbstractEntityGenerate> List<Map> queryBySQL2(
			String sql, Object[] queryParams, int firstindex, int maxresult) {

		Query query = getEntityManager().createNativeQuery(sql);
		setQueryParams(query, queryParams);
		if (firstindex >= 0) {
			query.setFirstResult(firstindex);
		}
		if (maxresult > 0) {
			query.setMaxResults(maxresult);
		}
		query.unwrap(SQLQuery.class).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);

		return query.getResultList();
	}

	/**
	 * 根据SQL查询总数
	 * 
	 * @Title: queryBySQL
	 * @Description: TODO
	 * @param @param countSQL
	 * @param @param queryParams
	 * @param @return
	 * @return long
	 * @throws
	 * @date 2014年6月18日 上午11:25:17
	 */
	public long countBySQL(String countSQL, Object[] queryParams) {

		Query query = getEntityManager().createNativeQuery(countSQL);
		setQueryParams(query, queryParams);
		BigInteger count = (BigInteger) query.getSingleResult();
		return count.longValue();
	}

	public <T extends AbstractEntityGenerate> List<Object[]> queryBySQL(
			String countSQL, String selectSQL, Object[] queryParams, Pages pages) {

		if (pages.getReCount() <= 0) {
			long count = countBySQL(countSQL, queryParams);
			pages.setReCount(count);
			pages.account();
		}
		if (pages.getReCount() == 0) {
			return new ArrayList(0);
		}

		int firstindex = (pages.getPage() - 1) * pages.getPageSize();
		int maxresult = pages.getPageSize();

		return queryBySQL(selectSQL, queryParams, firstindex, maxresult);
	}

	public <T extends AbstractEntityGenerate> List<Map> queryBySQL2(
			String countSQL, String selectSQL, Object[] queryParams, Pages pages) {

		if (pages.getReCount() <= 0) {
			long count = countBySQL(countSQL, queryParams);
			pages.setReCount(count);
			pages.account();
		}
		if (pages.getReCount() == 0) {
			return new ArrayList(0);
		}

		int firstindex = (pages.getPage() - 1) * pages.getPageSize();
		int maxresult = pages.getPageSize();

		return queryBySQL2(selectSQL, queryParams, firstindex, maxresult);
	}

	public <T extends AbstractEntityGenerate> List<Object[]> queryBySQL(
			String selectSQL, Object[] queryParams) {
		return queryBySQL(selectSQL, queryParams, -1, -1);
	}

	public <T extends AbstractEntityGenerate> List<Map> queryBySQL2(
			String selectSQL, Object[] queryParams) {
		return queryBySQL2(selectSQL, queryParams, -1, -1);
	}

	public <T extends AbstractEntityGenerate> List<Object[]> queryBySQL(
			String selectSQL) {
		return queryBySQL(selectSQL, null, -1, -1);
	}

	public <T extends AbstractEntityGenerate> List<Map> queryBySQL2(
			String selectSQL) {
		return queryBySQL2(selectSQL, null, -1, -1);
	}

	public <T extends AbstractEntityGenerate> List<Object[]> queryBySQL(
			String countSQL, String selectSQL, Pages pages) {
		return queryBySQL(countSQL, selectSQL, null, pages);
	}

	public <T extends AbstractEntityGenerate> List<Map> queryBySQL2(
			String countSQL, String selectSQL, Pages pages) {
		return queryBySQL2(countSQL, selectSQL, null, pages);
	}

	/**
	 * 根据SQL查询结果
	 * 
	 * @Title: queryBySQL
	 * @Description: TODO
	 * @param @param sql
	 * @param @param firstindex
	 * @param @param maxresult
	 * @param @return
	 * @return List<Map>
	 * @throws
	 * @date 2014年6月18日 上午11:21:05
	 */
	public <T extends AbstractEntityGenerate> Map querySingleBySQL2(String sql,
			Object[] queryParams) {

		List<Map> list = queryBySQL2(sql, queryParams, 0, 1);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	private long countByJPQL(String countSQL, Object[] queryParams) {

		Query query = getEntityManager().createQuery(countSQL);
		setQueryParams(query, queryParams);
		Long count = (Long) query.getSingleResult();
		return count.longValue();
	}

	/**
	 * 根据传入的jpql语句进行查询
	 * 
	 * @Title: queryByJPQL
	 * @Description: TODO
	 * @param @param jpql
	 * @param @param queryParams
	 * @param @param firstindex
	 * @param @param maxresult
	 * @param @return
	 * @return List<T>
	 * @throws
	 * @date 2014年6月18日 下午6:35:22
	 */
	private <T extends AbstractEntityGenerate> List<T> queryByJPQL(String jpql,
			Object[] queryParams, int firstindex, int maxresult) {

		Query query = getEntityManager().createQuery(jpql);
		setQueryParams(query, queryParams);
		if (firstindex >= 0) {
			query.setFirstResult(firstindex);
		}
		if (maxresult > 0) {
			query.setMaxResults(maxresult);
		}

		return query.getResultList();
	}

	private <T extends AbstractEntityGenerate> List<T> queryByJPQL(String jpql,
			Object queryParams, int firstindex, int maxresult) {

		Query query = getEntityManager().createQuery(jpql);
		setQueryParams(query, queryParams);
		if (firstindex >= 0) {
			query.setFirstResult(firstindex);
		}
		if (maxresult > 0) {
			query.setMaxResults(maxresult);
		}

		return query.getResultList();
	}

	public <T extends AbstractEntityGenerate> List<T> queryByJPQL(
			String countJPQL, String selectJPQL, Object[] queryParams,
			Pages pages) {

		if (pages.getReCount() <= 0) {
			long count = countByJPQL(countJPQL, queryParams);
			pages.setReCount(count);
			pages.account();
		}
		if (pages.getReCount() == 0) {
			return new ArrayList(0);
		}

		int firstindex = (pages.getPage() - 1) * pages.getPageSize();
		int maxresult = pages.getPageSize();

		return queryByJPQL(selectJPQL, queryParams, firstindex, maxresult);
	}

	public <T extends AbstractEntityGenerate> List<T> queryByJPQL(
			String selectJPQL, Object[] queryParams) {
		return queryByJPQL(selectJPQL, queryParams, -1, -1);
	}

	public <T extends AbstractEntityGenerate> List<T> queryByJPQL(
			String selectJPQL, Map queryParams) {
		return queryByJPQL(selectJPQL, queryParams, -1, -1);
	}

	public <T extends AbstractEntityGenerate> List<T> queryByJPQL(
			String selectJPQL) {
		return queryByJPQL(selectJPQL, null, -1, -1);
	}

	public <T extends AbstractEntityGenerate> List<T> queryByJPQL(
			String countJPQL, String selectJPQL, Pages pages) {
		return queryByJPQL(countJPQL, selectJPQL, null, pages);
	}

	/**
	 * 获取一条数据
	 * 
	 * @Title: getSingleResult
	 * @Description: TODO
	 * @param @param entityClass
	 * @param @param wherejpql
	 * @param @param queryParams
	 * @param @return
	 * @return T
	 * @throws
	 * @date 2014年6月18日 下午2:11:52
	 */
	public <T extends AbstractEntityGenerate> T getSingleResult(
			Class<T> entityClass, String wherejpql, Object[] queryParams) {

		String entityname = sqlBuilder.getEntityName(entityClass);
		Query query = getEntityManager().createQuery(
				"SELECT o FROM "
						+ entityname
						+ " o "
						+ (StringUtils.isEmpty(wherejpql) ? "" : "WHERE "
								+ wherejpql));
		setQueryParams(query, queryParams);
		try {
			return (T) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * getSingleResult 设置查询参数
	 * 
	 * @param query
	 *            查询
	 * @param queryParams
	 *            查询参数
	 */
	protected void setQueryParams(Query query, Object[] queryParams) {
		sqlBuilder.setQueryParams(query, queryParams);
	}

	protected void setQueryParams(Query query, Object queryParams) {
		sqlBuilder.setQueryParams(query, queryParams);
	}

	public <T extends AbstractEntityGenerate> T load(Class<T> entityClass,
			Object entityId) {
		try {
			return getEntityManager().getReference(entityClass, entityId);
		} catch (Exception e) {
			return null;
		}
	}

	public <T extends AbstractEntityGenerate> T findByWhere(
			Class<T> entityClass, String where, Object[] params) {
		List<T> l = getScrollData(entityClass, where, params);
		if (l != null && l.size() == 1) {
			return l.get(0);
		} else if (l.size() > 1) {
			throw new RuntimeException("查寻到的结果不止一个.");
		} else {
			return null;
		}
	}

	/**
	 * 执行更新或删除的语句
	 * 
	 * @Title: executeJPQL
	 * @Description: TODO
	 * @param @param jpql
	 * @param @return
	 * @return int
	 * @throws
	 * @date 2014年7月1日 下午3:44:36
	 */
	public int executeJPQL(String jpql) {
		return executeJPQL(jpql, null);
	}

	public int executeJPQL(String jpql, Object[] params) {
		Query query = getEntityManager().createQuery(jpql);
		if (params != null && params.length > 0) {
			setQueryParams(query, params);
		}
		return query.executeUpdate();
	}

	@Override
	public int executeSQL(String sql) {
		return executeSQL(sql, null);
	}

	@Override
	public int executeSQL(String sql, Object[] params) {

		Query query = getEntityManager().createNativeQuery(sql);
		if (params != null && params.length > 0) {
			setQueryParams(query, params);
		}
		return query.executeUpdate();
	}


}
