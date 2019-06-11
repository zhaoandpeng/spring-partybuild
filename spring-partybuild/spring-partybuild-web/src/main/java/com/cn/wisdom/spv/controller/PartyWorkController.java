package com.cn.wisdom.spv.controller;

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
 * 党务公开
 * @author MR.ZHAO
 *
 */
@Controller
@RequestMapping("/api/v1/spv/partywork")
public class PartyWorkController extends BaseController{

	@Resource
	private InformNoticeService informNoticeService;
	

	@RequestMapping(value = "/index")
	public String index() {
		
		return "spv/partywork/index";
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
		
		map.put("type", "13");
		
		page = informNoticeService.getListObjectPage(InfoPartyArticle.class, map, page);
		
		Stream<InfoPartyArticle> stream = page.getResult().stream();
		
		List<InfoPartyArticle> resultList= stream.filter((InfoPartyArticle model)-> !model.getStatus().equals("0")).collect(Collectors.toList());
		
		return toJson(resultList==null?null:resultList,resultList==null?0:resultList.size());
	}
	
	@SuppressWarnings({ "rawtypes"})
	@ResponseBody
	@RequestMapping("/ajax/getList")
	public String ajaxGetList() {
		
		String pageNo = getRequest().getParameter("page");
		
		String pageSize = getRequest().getParameter("limit");
		
		String status = getRequest().getParameter("status");
		
		String nodeId = getRequest().getParameter("nodeId"); //根据组织机构节点查询可查看的信息列表
		
		PageHelper page = new PageHelper();
		
		page.setPageNo(Integer.parseInt(pageNo));
		
		page.setPageSize(Integer.parseInt(pageSize));
		
		page.setOrderBy("orderNo");
		
		page = informNoticeService.getListPageByParam(status, nodeId, "13", page);
		
		return toJson(page.getResult()==null?null:page.getResult(),page.getResult()==null?0:page.getResult().size());
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
		String read = getRequest().getParameter("isRead");
		global = informNoticeService.busihandle(id,title,articleSource,createTime,content,read, "13", getCurrentBaseUser());
		resultMap.put("status", global);
		return toJson(resultMap);
	}
}
