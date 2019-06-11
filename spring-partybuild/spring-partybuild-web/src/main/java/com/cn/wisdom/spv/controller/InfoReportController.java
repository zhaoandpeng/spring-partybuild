package com.cn.wisdom.spv.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.wisdom.base.controller.BaseController;
import com.cn.wisdom.spv.model.InfoReport;
import com.cn.wisdom.spv.service.InfoReportService;
import com.cn.wisdom.utils.EventType;
import com.cn.wisdom.utils.PageHelper;
/**
 * 举报信息处理
 * @author MR.ZHAO
 *
 */
@Controller
@RequestMapping("/api/v1/spv/report")
public class InfoReportController extends BaseController{
	
	@Resource
	private InfoReportService infoReportService;

	@RequestMapping(value = "/index")
	public String index() {
		
		return "spv/report/index";
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
		
		page.setOrderBy("order_no");
		
		page = infoReportService.getListObjectPage(InfoReport.class, map, page);
	  
		List<InfoReport> resultList= page.getResult();
		
		return toJson(resultList==null?null:resultList,resultList==null?0:resultList.size());
	}
	
	@ResponseBody
	@RequestMapping(value = "/add_or_update")
	public String add_or_update(InfoReport report) throws SQLException {
		boolean global = false;
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		if(StringUtils.isBlank(report.getId())) {
			report.setId(UUID.randomUUID().toString());
			report.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));;
			report.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));;
			global = infoReportService.saveOrUpdate(report, EventType.EVENT_ADD);
		}else {
			global = infoReportService.saveOrUpdate(report, EventType.EVENT_UPDATE);
		}
		resultMap.put("status", global);
		return toJson(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "/del/{id}")
	public String del(@PathVariable String id) throws SQLException {
		boolean global = false;
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		if(StringUtils.isNotBlank(id)) {
			InfoReport model = infoReportService.get(InfoReport.class, id);
			 if(null!=model) {
				 global = infoReportService.delete(model);
			 }
		}
		resultMap.put("status", global);
		return toJson(resultMap);
	}
}
