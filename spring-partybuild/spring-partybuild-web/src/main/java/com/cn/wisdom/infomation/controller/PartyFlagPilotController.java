package com.cn.wisdom.infomation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.wisdom.base.controller.BaseController;
/**
 * @author MR.ZHAO
 * 党旗领航
 */
@Controller
@RequestMapping("/api/v1/information/partyFlagPilot")
public class PartyFlagPilotController extends BaseController {

	@RequestMapping(value = "/index")
	public String index_view() {
		
		return "infomation/partyFlagPilot/index";
	}
}
