package com.lishao.system.core.base;

import java.util.List;
import java.util.Map;

import com.lishao.system.core.mybatis.Page;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public interface BaseProvider<T extends BaseModel> {
	@Transactional
	public T update(T record);

	@Transactional
	public void delete(Long id, Long userId);

	public T queryById(Long id);

	public Page<T> query(Map<String, Object> params);

	public <K> Page<K> getPage(Page<Long> ids, Class<K> cls);

	public <K> List<K> getList(List<Long> ids, Class<K> cls);
	
	public List<T> getList(List<Long> ids);

	public List<T> queryAll(Map<String, Object> params);
}
