package com.cn.wisdom.infomation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MR.ZHAO
 * 党建地图
 */
@Controller
@RequestMapping("/api/v1/information/partymap")
public class PartyBuildMapController {

	@RequestMapping(value = "/index")
	public String index() {
		
		return "infomation/partymap/index";
	}
}
