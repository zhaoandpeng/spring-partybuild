package com.cn.wisdom.infomation.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.wisdom.base.controller.BaseController;
import com.cn.wisdom.info.model.InfoPartyArticle;
import com.cn.wisdom.infomation.service.InformNoticeService;
import com.cn.wisdom.utils.PageHelper;
/**
 * @author MR.ZHAO
 * 通知公告
 */
@Controller
@RequestMapping("/api/v1/information/notice")
public class InformNoticeController extends BaseController{

	
	@Resource
	private InformNoticeService informNoticeService;
	
	
	@RequestMapping(value = "/index")
	public String index() {
		
		return "infomation/notice/index";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping("/index/view")
	public String index_view() {
		
		ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
		
		String pageNo = getRequest().getParameter("page");
		
		String pageSize = getRequest().getParameter("limit");
		
		PageHelper page = new PageHelper();
		
		page.setPageNo(Integer.parseInt(pageNo));
		
		page.setPageSize(Integer.parseInt(pageSize));
		
		page.setOrderBy("orderNo");
		
		page = informNoticeService.getListObjectPage(InfoPartyArticle.class, map, page);
		
		Stream<InfoPartyArticle> stream = page.getResult().stream();
		
		List<InfoPartyArticle> resultList= stream.filter((InfoPartyArticle model)-> !model.getStatus().equals("0")).collect(Collectors.toList());
		
		return toJson(resultList==null?null:resultList,resultList==null?0:resultList.size());
	}
	
	@ResponseBody
	@RequestMapping(value = "/add_or_update")
	public String add_or_update() throws SQLException {
		boolean global = false;
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		String id = getRequest().getParameter("id");
		String title = getRequest().getParameter("title"); // 父级选项码标识
		String articleSource = getRequest().getParameter("articleSource");
		String createTime = getRequest().getParameter("createTime");
		String content = getRequest().getParameter("content");
		String read = getRequest().getParameter("read");
		global = informNoticeService.busihandle(id,title,articleSource,createTime,content,read, "0", getCurrentBaseUser());
		resultMap.put("status", global);
//		String desc = getRequest().getParameter("DESC");
		/*
		 * if(!StringUtil.isNullOrEmpty(id)) { BaseDictionary model =
		 * baseDictionaryService.get(BaseDictionary.class, id); if(null!=model) {
		 * model.setPID(pid); model.setITEM_CODE(itemCode);
		 * model.setITEM_NAME(itemName); model.setITEM_VALUE(itemValue);
		 * model.setSTATUS(status); model.setREMARK(desc); global =
		 * this.baseDictionaryService.saveOrUpdate(model,EventType.EVENT_UPDATE);//
		 * 执行更新操作 } }else { BaseDictionary model = new BaseDictionary();
		 * model.setID(UUID.randomUUID().toString()); model.setPID(pid);
		 * model.setITEM_CODE(itemCode); model.setITEM_NAME(itemName);
		 * model.setITEM_VALUE(itemValue); model.setSTATUS(status);
		 * model.setREMARK(desc); global =
		 * this.baseDictionaryService.saveOrUpdate(model,EventType.EVENT_ADD);//执行新增操作 }
		 * resultMap.put("status", global);
		 */
		return toJson(resultMap);
	}
}
