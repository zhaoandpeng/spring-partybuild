package com.cn.wisdom.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.wisdom.base.dao.BaseDao;
import com.cn.wisdom.base.model.BaseOrganization;
import com.cn.wisdom.base.service.BaseOrganizationService;
import com.cn.wisdom.infomation.dao.BaseOrganizationDao;
@Service
public class BaseOrganizationServiceImpl extends BaseServiceImpl<BaseOrganization,java.lang.String> implements BaseOrganizationService{

	
	/*
	 * @Resource private BaseOrganizationDao baseOrganizationDao;
	 * 
	 * 
	 * @Override public void deleteByIds(List<String> ids) {
	 * 
	 * StringBuffer buffer = new StringBuffer();
	 * 
	 * if(!ids.isEmpty()) {
	 * 
	 * for (String string : ids) {
	 * 
	 * buffer.append(" delete from base_organization where id = '"+string+"'");
	 * 
	 * baseOrganizationDao.deleteById }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 */

}
