package com.cn.wisdom.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.wisdom.base.model.BaseOrganization;
import com.cn.wisdom.base.service.BaseOrganizationService;
import com.cn.wisdom.infomation.dao.BaseOrganizationDao;
@Service
public class BaseOrganizationServiceImpl extends BaseServiceImpl<BaseOrganization,java.lang.String> implements BaseOrganizationService{

	
	@Resource
	private BaseOrganizationDao baseOrganizationDao;
	
	@Override
	public int delBatchByPrimary(String id) {
		
		return baseOrganizationDao.delBatchByPrimary(id);
	}

}
