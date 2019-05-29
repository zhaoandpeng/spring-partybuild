package com.cn.wisdom.base.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.cn.wisdom.utils.PageHelper;

public interface BaseDao<T, K extends Serializable> {

	T get(Class<T> clazz, K key) throws SQLException ;

	boolean saveOrUpdate(T t, K key);

	boolean delete(T t);
	
	int deleteBatch(final List<T> list, ConcurrentHashMap<String,Object> map);
	
	int deleteBatchByPrimary(final Class<T> clazz, List<K> list);

	List<T> getList(Class<T> clazz, ConcurrentHashMap<String,Object> map);
	
	PageHelper<T> getListObjectPage(Class<T> clazz, ConcurrentHashMap<String,Object> map, PageHelper<T> pageModel );
	
	PageHelper<T> getListMapPage(StringBuffer sql, PageHelper<T> pageModel );
	
}
