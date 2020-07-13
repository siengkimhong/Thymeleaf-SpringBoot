package com.kimhong.thymeleaf;

import com.kimhong.thymeleaf.model.Testing;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TestController {
    @GetMapping("/")
    public String index(){
        return "sample";
    }

    @GetMapping("/test")
    public String testView(@ModelAttribute Testing testing, ModelMap map){
        map.addAttribute("testing", testing);
        return "testing";
    }

    @PostMapping("/test")
    public String testAction(@Valid @ModelAttribute Testing testing, BindingResult result, ModelMap map){
        if (result.hasErrors()){
            System.out.println(result.getFieldErrors());
            return testView(testing, map);
        }
        System.out.println(testing);
        return "redirect:/test";
    }

}
