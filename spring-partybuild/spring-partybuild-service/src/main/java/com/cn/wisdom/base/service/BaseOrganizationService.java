package com.cn.wisdom.base.service;

import com.cn.wisdom.base.model.BaseOrganization;

public interface BaseOrganizationService extends BaseService<BaseOrganization, java.lang.String>{

	int delBatchByPrimary(String id);

}
