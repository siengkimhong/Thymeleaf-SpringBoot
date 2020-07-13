package com.kimhong.thymeleaf.controller.admin;
import com.kimhong.thymeleaf.model.User;
import com.kimhong.thymeleaf.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final String ADD_USER_VIEW = "/admin/user-add";
    private final String ADD_USER_URL = "/admin/user/add";
    private final UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addUserView(@ModelAttribute User user,
                              @ModelAttribute String confirm,
                              ModelMap map){
        map.addAttribute("user", user);
        map.addAttribute("confirm", confirm);
        return ADD_USER_VIEW;
    }

    @PostMapping("/add")
    public String addUserAction(User user, String confirm,
                                RedirectAttributes redirect,
                                BindingResult result
                                ){

        if (result.hasErrors()){
            return ADD_USER_VIEW;
        }
        if (!user.getPassword().equals(confirm)){
            redirect.addFlashAttribute("pwdError", true);
            redirect.addFlashAttribute("user", user);
            redirect.addFlashAttribute("message", "Password doesn't match");
            return "redirect:" + ADD_USER_URL;
        }

        user.setUserId(UUID.randomUUID().toString());
        userService.saveUser(user);
        return ADD_USER_VIEW;
    }
}
