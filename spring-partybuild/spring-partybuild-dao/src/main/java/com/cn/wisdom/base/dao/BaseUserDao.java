package com.cn.wisdom.base.dao;

import com.cn.wisdom.base.model.BaseUser;

public interface BaseUserDao extends BaseDao<BaseUser, java.lang.String>{

	BaseUser getBaseUserByParam(String username);

}
