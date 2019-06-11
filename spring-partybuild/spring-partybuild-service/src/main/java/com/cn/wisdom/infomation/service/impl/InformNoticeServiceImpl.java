package com.cn.wisdom.infomation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cn.wisdom.base.dao.BaseDao;
import com.cn.wisdom.base.model.BaseUser;
import com.cn.wisdom.base.service.impl.BaseServiceImpl;
import com.cn.wisdom.info.model.InfoPartyArticle;
import com.cn.wisdom.infomation.service.InformNoticeService;
import com.cn.wisdom.utils.EventType;
import com.cn.wisdom.utils.PageHelper;
@Service
public class InformNoticeServiceImpl  extends BaseServiceImpl<InfoPartyArticle,java.lang.String> implements InformNoticeService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource(name = "baseDaoImpl")
	private BaseDao baseDao;

	@SuppressWarnings("unchecked")
	@Override
	public boolean busihandle(String id, String title, String articleSource, String createTime, String content, String read, String type, BaseUser baseUser) {
		
		boolean global = true;	List<String> listImgSrc = new ArrayList<String>();
		
		if(StringUtils.isNotBlank(id)) {
			
			//修改
			
			
			return global;
		}
		
		/**
		 * 处理内容信息
		 */
		if(StringUtils.isNotBlank(content)) {
			
			//图片链接验证表达式

			String img = "";	
			
			String regEx = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
			
			Pattern pattern = Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
			
			Matcher matcher = pattern.matcher(content);
			
			while (matcher.find()) {
	            
	            img = matcher.group();// 得到<img />数据
	            
	            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);// 匹配<img>中的src数据
	            
	            while (m.find()) {
	            	
	            	listImgSrc.add(m.group(1));
	            }
	        }
		}
		
		InfoPartyArticle model = new InfoPartyArticle();

		model.setId(UUID.randomUUID().toString());
		model.setTitle(title);
		model.setCheckerName("");
		model.setCheckerUserNo("");
		model.setArticleSource(articleSource);
		model.setCreateTime(createTime);
		model.setCreateName(baseUser.getUsername());
		model.setCreateUserNo(baseUser.getUsername());
		model.setContent(content);
		model.setIsRead(read);
		model.setReceiveOrgId("");
//		model.setStatus("");
		model.setType(type);
		
		global = baseDao.saveOrUpdate(model, EventType.EVENT_ADD);
		
		return global;
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageHelper getListPageByParam(String status, String nodeId, String type, PageHelper page) {
		
		StringBuffer buffer = new StringBuffer("select * from info_partyarticle where type = '").append(type+"'");
		
		if(StringUtils.isNotBlank(status)) {
			
			buffer.append(" and status = '"+status+"'");
		}
		
		if(StringUtils.isNotBlank(nodeId)) {
			
			buffer.append(" and find_in_set( '").append(nodeId).append("', receiveOrgId)");
		}
		
		PageHelper listModel = super.getListObjectPageBySql(InfoPartyArticle.class, buffer.toString(), page);
		
		return listModel;
	}

}
