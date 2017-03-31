package com.lishao.system.core.base;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lishao.system.core.Constants;
import com.lishao.system.core.mybatis.Page;
import com.lishao.system.core.support.Assert;
import com.lishao.system.core.util.RedissonUtil;
import com.lishao.system.core.util.WebUtil;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class BaseService<P extends BaseProvider<T>, T extends BaseModel> {
	protected Logger logger = LogManager.getLogger();
	protected P provider;

	/** 修改 */
	public void update(T record) {
		Assert.notNull(record.getRowId(), "ID");
		provider.update(record);
	}

	/** 新增 */
	public void add(T record) {
		provider.update(record);
	}

	/** 删除 */
	public void delete(Long id) {
		Assert.notNull(id, "ID");
		provider.delete(id, WebUtil.getCurrentUserId());
	}

	/** 根据Id查询 */
	@SuppressWarnings("unchecked")
	public T queryById(Long id) {
		Assert.notNull(id, "ID");
		StringBuilder sb = new StringBuilder(Constants.CACHE_NAMESPACE);
		String className = this.getClass().getSimpleName().replace("Service", "");
		sb.append(className.substring(0, 1).toLowerCase()).append(className.substring(1));
		sb.append(":").append(id);
		T record = (T) RedissonUtil.get(sb.toString());
		if (record == null) {
			record = provider.queryById(id);
		}
		return record;
	}

	/** 条件查询 */
	public Page<T> query(Map<String, Object> params) {
		return provider.query(params);
	}
	/**
	 * 条件查询，不分页
	 */
	public List<T> queryAll(Map<String, Object> params) {
		return provider.queryAll(params);
	}
}
