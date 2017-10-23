package com.daniel.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RestController combines @Controller and @ResponseBody

@RequestMapping("/hello")
public class HelloController {
	
    @RequestMapping("/test")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
