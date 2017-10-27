package com.daniel.controller;

import io.swagger.annotations.ApiOperation;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RestController combines @Controller and @ResponseBody

@RequestMapping("/hello")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	//	method : 指定对应Rest 的哪个方法；
	//	value : 指定路径
    @ApiOperation(value="API_Value", notes="API_Notes")
	@ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }
	
	@RequestMapping("/")
	public String indexHome() {
		return "hello";
	}
	
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name",required = false,defaultValue = "world") String name,Model model) {
		model.addAttribute("name",name);
		return "greeting";
	}

}
