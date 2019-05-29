package com.cn.wisdom.base.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.cn.wisdom.base.dao.BaseMenuDao;
import com.cn.wisdom.base.dao.BaseRoleDao;
import com.cn.wisdom.base.dao.BaseRoleResourcesDao;
import com.cn.wisdom.base.dao.BaseUserDao;
import com.cn.wisdom.base.dao.BaseUserRoleDao;
import com.cn.wisdom.base.model.BaseMenu;
import com.cn.wisdom.base.model.BaseRole;
import com.cn.wisdom.base.model.BaseRoleResources;
import com.cn.wisdom.base.model.BaseUser;
import com.cn.wisdom.base.model.BaseUserRole;
import com.cn.wisdom.base.service.BaseUserService;

@Service
public class BaseUserServiceImpl extends BaseServiceImpl<BaseUser,java.lang.String> implements BaseUserService, UserDetailsService{
	
	@Resource
	private BaseUserDao baseUserDao;
	
	@Resource
	private BaseUserRoleDao baseUserRoleDao;
	
	@Resource
	private BaseRoleDao baseRoleDao;
	
	@Resource
	private BaseMenuDao baseMenuDao;
	
	@Resource
	private BaseRoleResourcesDao baseRoleResourcesDao;
	
	@Resource 
	private PasswordEncoder passwordEncoder;
	 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		BaseUser baseUser = null;
		
		baseUser = this.baseUserDao.getBaseUserByParam(username);
		
		Assert.notNull(baseUser, "找不到该账户信息！");
		
		//查询用户角色
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		
		List<BaseMenu> listResoure = new ArrayList<BaseMenu>();
		
		ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
		
		map.put("USERID", baseUser.getId());
		
		List<BaseUserRole> listRole = this.baseUserRoleDao.getList(BaseUserRole.class, map);
		
		if(!listRole.isEmpty()) {
		
			for (BaseUserRole role: listRole) {
				
				String roleName = null;
				
				try {
					
					roleName = this.baseRoleDao.get(BaseRole.class, role.getRoleid()).getRoleName();
					
					map.clear();
					
					map.put("ROLE_ID", role.getRoleid());
					
					List<BaseRoleResources> roleResoure = this.baseRoleResourcesDao.getList(BaseRoleResources.class, map);
					
					for (BaseRoleResources baseRoleResources : roleResoure) {
						
						BaseMenu baseMenu = this.baseMenuDao.get(BaseMenu.class, baseRoleResources.getMenuId());
						
						listResoure.add(baseMenu);
					}
					
				
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				list.add(new SimpleGrantedAuthority("ROLE_"+roleName));
			}
			
			baseUser.setRole(list);
			
			baseUser.setRoleResources(listResoure);
		}
		
		baseUser.setPassword(passwordEncoder.encode(baseUser.getPassword()));
		
		return baseUser;
	}


}
