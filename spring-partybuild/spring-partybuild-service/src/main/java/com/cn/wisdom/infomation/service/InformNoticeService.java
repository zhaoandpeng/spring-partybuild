package com.cn.wisdom.infomation.service;

import com.cn.wisdom.base.model.BaseUser;
import com.cn.wisdom.base.service.BaseService;
import com.cn.wisdom.info.model.InfoPartyArticle;

public interface InformNoticeService extends BaseService<InfoPartyArticle, java.lang.String>{

	boolean busihandle(String id, String title, String articleSource, String createTime, String content, String read, String type, BaseUser baseUser);

}
