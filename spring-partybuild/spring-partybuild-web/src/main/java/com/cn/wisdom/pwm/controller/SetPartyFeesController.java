package com.cn.wisdom.pwm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.wisdom.base.controller.BaseController;
/**
 * @author MR.ZHAO
 * 党费设置
 */
@Controller
@RequestMapping("/api/v1/pwm/set_party_fees")
public class SetPartyFeesController extends BaseController{

	
	
	@RequestMapping(value = "/index")
	public String index() {
		
		return "pwm/setPartyFees/index";
	}
}
