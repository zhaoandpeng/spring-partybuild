package com.cn.wisdom.base.service;

import java.util.concurrent.ConcurrentHashMap;

import com.cn.wisdom.base.model.BaseDictionary;
import com.cn.wisdom.utils.PageHelper;

public interface BaseDictionaryService extends BaseService<BaseDictionary, java.lang.String>{

	@SuppressWarnings("rawtypes")
	public PageHelper getListMapByPage(ConcurrentHashMap<String,Object> map, PageHelper page);
}
