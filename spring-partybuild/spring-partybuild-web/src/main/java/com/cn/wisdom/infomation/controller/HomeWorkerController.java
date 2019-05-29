package com.cn.wisdom.infomation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.wisdom.base.controller.BaseController;
/**
 * @author MR.ZHAO
 * 职工之家
 */
@Controller
@RequestMapping("/api/v1/information/homeWorker")
public class HomeWorkerController extends BaseController {

	@RequestMapping(value = "/index")
	public String index_view() {
		
		return "infomation/homeWorker/index";
	}
}
