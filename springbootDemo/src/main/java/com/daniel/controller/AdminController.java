package com.daniel.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *   
 * 
 * @Title: CityRestController.java 
 * @Package com.bamboo.springboot.controller 
 * @Description: 用户登陆权限认证管理控制器
 * @author bamboo  <a href=
 *         "mailto:zjcjava@163.com?subject=hello,bamboo&body=Dear Bamboo:%0d%0a描述你的问题："
 *         >Bamboo</a>   
 * @date 2017年4月21日 下午5:51:36 
 * @version V1.0   
 */
@RestController
public class AdminController {

	@RequestMapping("/admin")
	public ModelAndView admin(Model model) {
		ModelAndView mav = new ModelAndView("admin/test");
		// return "login";
		return mav;
	}

}
