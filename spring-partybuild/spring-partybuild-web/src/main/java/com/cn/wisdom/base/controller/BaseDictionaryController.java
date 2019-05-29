package com.cn.wisdom.base.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.wisdom.base.model.BaseDictionary;
import com.cn.wisdom.base.service.BaseDictionaryService;
import com.cn.wisdom.utils.EventType;
import com.cn.wisdom.utils.PageHelper;

import io.netty.util.internal.StringUtil;

@Controller
@RequestMapping(value = "/api/v1/sys/dict")
public class BaseDictionaryController extends BaseController{

	
	@Resource
	private BaseDictionaryService baseDictionaryService;
	
	
	@RequestMapping(value = "/index")
	public String index() {
		
		return "system/dict/index";
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/index/view")
	public String index_view() {
		
		String pageNo = getRequest().getParameter("page");
		
		String pageSize = getRequest().getParameter("limit");
		
		PageHelper page = new PageHelper();
		
		page.setPageNo(Integer.parseInt(pageNo));
		
		page.setPageSize(Integer.parseInt(pageSize));
		
		page = baseDictionaryService.getListMapByPage(null, page);
		
		return toJson(page.getResult(),(int)page.getTotalCount());
	}
	
	
	@ResponseBody
	@RequestMapping("/get/item/code")
	public String get_item_code() {
		
		ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
		
		String pageNo = getRequest().getParameter("page");
		
		String pageSize = getRequest().getParameter("limit");
		
		PageHelper<BaseDictionary> page = new PageHelper<BaseDictionary>();
		
		page.setPageNo(Integer.parseInt(pageNo));
		
		page.setPageSize(Integer.parseInt(pageSize));
		
		map.put("PID", '0');
		
		map.put("STATUS", '1'); //默认查询启用状态的数据字典
		
		page = baseDictionaryService.getListObjectPage(BaseDictionary.class, map, page);
		
		return toJson(page.getResult(),(int)page.getTotalCount());
	}
	
	@ResponseBody
	@RequestMapping("/get/item/list/{parentCode}")
	public String getItemListByParentCode(@PathVariable("parentCode") String parentCode) {
		
		ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
		
		map.put("ITEM_CODE", parentCode);
		
		List<BaseDictionary> list = baseDictionaryService.getList(BaseDictionary.class, map);
		
		if(null!=list) {
			
			BaseDictionary model  = list.get(0);  map.clear();
			
			map.put("PID", model.getID());
			
			list = baseDictionaryService.getList(BaseDictionary.class, map);
			
		}
		
		return toJson(list,list==null? 0 : list.size());
	}
	
	@ResponseBody
	@RequestMapping(value = "/add_or_update")
	public String add_or_update() throws SQLException {
		boolean global = false;
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		String id = getRequest().getParameter("ID");
		String pid = getRequest().getParameter("PID"); // 父级选项码标识
		String itemCode = getRequest().getParameter("ITEM_CODE");
		String itemName = getRequest().getParameter("ITEM_NAME");
		String itemValue = getRequest().getParameter("ITEM_VALUE");
		String status = getRequest().getParameter("STATUS");
		String desc = getRequest().getParameter("DESC");
		if(!StringUtil.isNullOrEmpty(id)) {
			BaseDictionary model = baseDictionaryService.get(BaseDictionary.class, id);
			if(null!=model) {
				model.setPID(pid);
				model.setITEM_CODE(itemCode);
				model.setITEM_NAME(itemName);
				model.setITEM_VALUE(itemValue);
				model.setSTATUS(status);
				model.setREMARK(desc);
				global = this.baseDictionaryService.saveOrUpdate(model,EventType.EVENT_UPDATE);//执行更新操作
			}
		}else {
			BaseDictionary model = new BaseDictionary();
			model.setID(UUID.randomUUID().toString());
			model.setPID(pid);
			model.setITEM_CODE(itemCode);
			model.setITEM_NAME(itemName);
			model.setITEM_VALUE(itemValue);
			model.setSTATUS(status);
			model.setREMARK(desc);
			global = this.baseDictionaryService.saveOrUpdate(model,EventType.EVENT_ADD);//执行新增操作
		}
		resultMap.put("status", global);
		return toJson(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") String id) throws SQLException {
		
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		
		if(!StringUtil.isNullOrEmpty(id)) {
			
			String[] arr = id.split(",");
			
			for (String primary : arr) {
				
				BaseDictionary model = this.baseDictionaryService.get(BaseDictionary.class, primary);
				
				if(null!=model) {
					
					boolean delete = this.baseDictionaryService.delete(model);
					
					resultMap.put("status", delete);
				}
			}
		}
		
		if(StringUtil.isNullOrEmpty(id)) {
			
			resultMap.put("status", false); resultMap.put("message", "参数为空,请检查所传参数");
		}
		return toJson(resultMap);
	}
}
