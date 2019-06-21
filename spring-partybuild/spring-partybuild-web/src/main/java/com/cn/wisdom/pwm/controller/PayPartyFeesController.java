package com.cn.wisdom.pwm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.wisdom.base.controller.BaseController;
import com.cn.wisdom.pwm.service.PayPartyFeesService;
/**
 * @author MR.ZHAO
 * 党费催缴
 */
@Controller
@RequestMapping("/api/v1/pwm/pay_party_fees")
public class PayPartyFeesController extends BaseController{
	
	
	/*
	 * @Resource private PayPartyFeesService payPartyFeesService;
	 */
	 
	  @RequestMapping(value = "/index")
	  public String index() {

		  return "pwm/payPartyFees/index";
	  }
	
}
