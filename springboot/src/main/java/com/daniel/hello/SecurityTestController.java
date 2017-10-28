package com.daniel.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityTestController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
