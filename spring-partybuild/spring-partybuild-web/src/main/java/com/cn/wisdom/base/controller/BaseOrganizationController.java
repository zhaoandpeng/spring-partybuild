package com.cn.wisdom.base.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.wisdom.base.model.BaseOrganization;
import com.cn.wisdom.base.service.BaseOrganizationService;
import com.cn.wisdom.utils.EventType;
import com.cn.wisdom.utils.Tree;
import com.cn.wisdom.utils.TreeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/v1/sys/organization")
public class BaseOrganizationController extends BaseController{
	
	@Resource
	private BaseOrganizationService baseOrganizationService;
	

	@RequestMapping(value = "/index")
	public String index() {
		
		return "system/organization/index";
	}
	
	@ResponseBody
	@RequestMapping(value = "/index/view")
	public String index_view() {
		
		ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
		
		List<BaseOrganization> list = baseOrganizationService.getList(BaseOrganization.class, map);
		
		JSONObject obj = new JSONObject();
		
		obj.put("code", 0);
		
		obj.put("data", list);
		
		obj.put("is", true);
		
		return obj.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/tree/view")
	public String tree_view() {
		
		ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
		
		List<BaseOrganization> list = baseOrganizationService.getList(BaseOrganization.class, map);
		
		Tree<BaseOrganization> t = null;
		
		if(null!=list) {
			
			List<Tree<BaseOrganization>> trees = new ArrayList<Tree<BaseOrganization>>();
			
			for (BaseOrganization model : list) {
				Tree<BaseOrganization> tree = new Tree<BaseOrganization>();
				tree.setId(model.getId());
				tree.setParentId(model.getPid());
				tree.setTitle(model.getOrgName());
				tree.setCheckArr("[{\"type\": \"0\", \"checked\": \"0\"}]");
				trees.add(tree);
			}
			
			t = TreeUtils.build(trees);
			
		}
		
		JSONObject json = new JSONObject();
		
		JSONArray jsonArray = JSONArray.fromObject(t);
		
		json.put("data", jsonArray.toString());
		
		json.put("data", jsonArray.toString());
		
		json.put("status","{\"code\":200,\"message\":\"操作成功\"}");
		
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/add")
	public String add_or_update() throws SQLException {
		boolean global = false;
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		String id = getRequest().getParameter("id");
		String orgName = getRequest().getParameter("orgName");
		String address = getRequest().getParameter("address");
		String remark = getRequest().getParameter("remark");
		if(StringUtils.isNotBlank(id)&&StringUtils.isNotBlank(orgName)) {
			BaseOrganization model = new BaseOrganization();
			model.setId(UUID.randomUUID().toString());
			model.setPid(id);
			model.setOrgName(orgName);
			model.setAddress(address);
			model.setRemark(remark);
			global = baseOrganizationService.saveOrUpdate(model, EventType.EVENT_ADD);
		}
		resultMap.put("status", global);
		return toJson(resultMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "/del")
	public String del() throws SQLException {
		boolean global = false;
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		String id = getRequest().getParameter("id");
		String pid = getRequest().getParameter("pid");
		if(StringUtils.isNotBlank(id)&&StringUtils.isNotBlank(pid)) {
			int delCount = baseOrganizationService.delBatchByPrimary(id);
			if(delCount!=0) {
				global = true;
			}
		}
		resultMap.put("status", global);
		return toJson(resultMap);
	}
	
}
