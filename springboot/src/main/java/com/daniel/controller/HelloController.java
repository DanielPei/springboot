package com.daniel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET, value = "/test")
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
		return "/hello/greeting";
	}

}
