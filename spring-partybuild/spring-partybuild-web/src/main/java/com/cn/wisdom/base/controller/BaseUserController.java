package com.cn.wisdom.base.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.cn.wisdom.base.service.BaseUserService;

@Controller
public class BaseUserController {

	@Resource
	private BaseUserService baseUserService;
	
	public String getBaseUser() {
		
		
//		Object objectById = baseUserService.getObjectById("AAA");
		
		return "";
	}
	
	
}
