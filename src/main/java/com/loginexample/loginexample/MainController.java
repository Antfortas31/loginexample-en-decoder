package com.loginexample.loginexample;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String viewHomePage(){
        //System.out.println("Home Page");
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getAuthorities());
        return "index";
    }

    @RequestMapping("/manager")
    public String viewManagerPage(){
        //System.out.println("Manager Page");
        return "manager";
    }
    
    @RequestMapping("/403")
    public String view403Page(){
        //System.out.println("403 Page");
        return "403";
    }
}