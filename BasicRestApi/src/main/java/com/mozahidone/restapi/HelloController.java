package com.mozahidone.restapi;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello(HttpServletResponse httpServletResponse) {//To make it simple pagination has not been implemented
        return "Spring REST API...";
    }

}
