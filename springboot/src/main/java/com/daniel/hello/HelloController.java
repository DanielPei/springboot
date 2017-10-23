package com.daniel.hello;

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RestController combines @Controller and @ResponseBody

@RequestMapping("/hello")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	//	method : 指定对应Rest 的哪个方法；
	//	value : 指定路径
    @ApiOperation(value="API_Value", notes="API_Notes")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
