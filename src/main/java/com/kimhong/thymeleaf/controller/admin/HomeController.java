package com.kimhong.thymeleaf.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/admin")
    public String adminHome(){
        return "admin/index";
    }
}
