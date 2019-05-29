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
	@RequestMapping("/index/view")
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
        List<String> ids = new ArrayList<>(); // 声明存放需要删除的节点的容器
		ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
		ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
		String id = getRequest().getParameter("id");
		String pid = getRequest().getParameter("pid");
		if(StringUtils.isNotBlank(id)&&StringUtils.isNotBlank(pid)) {
			ids.add(id);	//把自己的id放到集合中
			this.getIds(id, ids, map);
			int delCount = baseOrganizationService.deleteBatchByPrimary(BaseOrganization.class, ids);
			if(delCount==ids.size()) {
				global = true;
			}
			/*
			 * BaseOrganization model = baseOrganizationService.get(BaseOrganization.class,
			 * id); global = baseOrganizationService.delete(model);
			 */
		}
		resultMap.put("status", global);
		return toJson(resultMap);
	}
	
	private void getIds(String id, List<String> ids, ConcurrentHashMap<String,Object> map) {
        // 根据条件查询当前节点的所有的子节点
		map.put("pid", id);
		List<BaseOrganization> listModel = baseOrganizationService.getList(BaseOrganization.class, map);
        // 使用递归的方式，必须设置递归的停止条件，否则会一直自己调用自己，直到内存溢出
        // 判断是否还有子节点
        if (listModel.size() > 0) {
            // 如果有子节点，遍历结果集
            for (BaseOrganization son : listModel) {
                // 1.把子节点的id放到ids容器中
                ids.add(son.getId());
                // 2.执行递归，自己调用自己，查询子节点的子
                map.clear();
                map.put("pid", son.getId());
                this.getIds(son.getId(), ids, map);
            }
        }
    }
}
