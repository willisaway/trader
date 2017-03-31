package com.lishao.system.core.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lishao.system.core.Constants;
import com.lishao.system.core.mybatis.Page;
import com.lishao.system.core.util.DataUtil;
import com.lishao.system.core.util.InstanceUtil;
import com.lishao.system.core.util.RedissonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;


/**
 * 业务逻辑层基类<br/>
 * 继承基类后必须配置CacheConfig(cacheNames="")
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public abstract class BaseProviderImpl<T extends BaseModel> implements
		BaseProvider<T> {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired
	protected BaseMapper<T> mapper;

	/** 分页查询 */
	public static Page<Long> getPage(Map<String, Object> params) {
		Integer current = 1;
		Integer size = 10;
		String orderBy = "";
		boolean isAsc=true;
		if (DataUtil.isNotEmpty(params.get("pageNum"))) {
			current = Integer.valueOf((String) params.get("pageNum"));
		}
		if (DataUtil.isNotEmpty(params.get("pageSize"))) {
			size = Integer.valueOf((String) params.get("pageSize"));
		}
		if (DataUtil.isNotEmpty(params.get("orderBy"))) {
			orderBy = (String) params.get("orderBy");
		}
		if (DataUtil.isNotEmpty(params.get("isAsc"))) {
			isAsc = Boolean.valueOf(params.get("isAsc").toString());
		}
		Page<Long> page = new Page<Long>(current, size, orderBy,isAsc);
		return page;
	}

	/** 初始化分页对象 2017年2月22日17:29:08 */
	public Page<T> initPage(Map<String, Object> params) {
		Integer current = 1;
		Integer size = 10;
		String orderBy = "";
		boolean isAsc=true;
		if (DataUtil.isNotEmpty(params.get("pageNum"))) {
			current = Integer.valueOf((String) params.get("pageNum"));
		}
		if (DataUtil.isNotEmpty(params.get("pageSize"))) {
			size = Integer.valueOf((String) params.get("pageSize"));
		}
		if (DataUtil.isNotEmpty(params.get("orderBy"))) {
			orderBy = (String) params.get("orderBy");
		}
		if (DataUtil.isNotEmpty(params.get("isAsc"))) {
			isAsc = Boolean.valueOf(params.get("isAsc").toString());
		}
		Page<T> page = new Page<T>(current, size, orderBy,isAsc);
		return page;
	}

	/** 根据Id查询(默认类型T) */
	public Page<T> getPage(Page<Long> ids) {
		if (ids != null) {
			Page<T> page = new Page<T>(ids.getCurrent(), ids.getSize());
			page.setTotal(ids.getTotal());
			List<T> records = InstanceUtil.newArrayList();
			for (Long id : ids.getRecords()) {
				records.add(this.queryById(id));
			}
			page.setRecords(records);
			return page;
		}
		return new Page<T>();
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> Page<K> getPage(Page<Long> ids, Class<K> cls) {
		if (ids != null) {
			Page<K> page = new Page<K>(ids.getCurrent(), ids.getSize());
			page.setTotal(ids.getTotal());
			List<K> records = InstanceUtil.newArrayList();
			for (Long id : ids.getRecords()) {
				T t = this.queryById(id);
				K k = InstanceUtil.to(t, cls);
				records.add(k);
			}
			page.setRecords(records);
			return page;
		}
		return new Page<K>();
	}

	/** 根据Id查询(默认类型T) */
	public List<T> getList(List<Long> ids) {
		List<T> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (Long id : ids) {
				list.add(this.queryById(id));
			}
		}
		return list;
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> List<K> getList(List<Long> ids, Class<K> cls) {
		List<K> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (Long id : ids) {
				T t = this.queryById(id);
				K k = InstanceUtil.to(t, cls);
				list.add(k);
			}
		}
		return list;
	}

	@Transactional
	public void delete(Long id) {
		try {
			T record = this.queryById(id);
			record.setDeletedFlag("1");
			record.setLastUpdDate(new Date());
			mapper.updateById(record);
			RedissonUtil.set(getCacheKey(id), record);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Transactional
	public T update(T record) {
		try {
			// record.setDeletedFlag("1");
			record.setLastUpdDate(new Date());
			if (record.getRowId() == null) {
				record.setCreatedDate(new Date());
				mapper.insert(record);
				record = queryById(record.getRowId());//数据库非主键字段的默认值,没有更新到bean中 2017年3月21日11:09:20 liyg
			} else {
				mapper.updateById(record);
			}
			RedissonUtil.set(getCacheKey(record.getRowId()), record);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return record;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public T queryById(Long id) {
		try {
			String key = getCacheKey(id);
			T record = (T) RedissonUtil.get(key);
			if (record == null) {
				record = mapper.selectById(id);
				RedissonUtil.set(key, record);
			}
			return record;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/** 获取缓存键值 */
	protected String getCacheKey(Object id) {
		String cacheName = null;
		CacheConfig cacheConfig = getClass().getAnnotation(CacheConfig.class);
		if (cacheConfig == null || cacheConfig.cacheNames() == null
				|| cacheConfig.cacheNames().length < 1) {
			cacheName = getClass().getName();
		} else {
			cacheName = cacheConfig.cacheNames()[0];
		}
		return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName)
				.append(":").append(id).toString();
	}

	/**
	 * 查询订单，带分页
	 */
	public Page<T> query(Map<String, Object> params) {
		Page<Long> idPage = getPage(params);
		idPage.setRecords(mapper.selectIdByMap(idPage, params));
		Page<T> pageInfo = getPage(idPage);
		return pageInfo;
	}

	/**
	 * 查询所有,不分页查询
	 * 
	 * @param params
	 * @return
	 */
	public List<T> queryAll(Map<String, Object> params) {
		List<Long> ids = mapper.selectIdByMap(params);
		List<T> resultList = getList(ids);
		return resultList;
	}
}
