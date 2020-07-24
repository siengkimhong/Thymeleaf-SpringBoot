package com.kimhong.thymeleaf.controller.admin;

import com.kimhong.thymeleaf.model.User;
import com.kimhong.thymeleaf.service.Imp.UserServiceImp;
import com.kimhong.thymeleaf.utils.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final String ADD_USER_VIEW = "/admin/user-add";
    private final String ADD_USER_URL = "/admin/users/add";
    private final String VIEW_USER_URL = "/admin/users";
    private final UserServiceImp userService;


    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addUserView(@ModelAttribute User user,
                              ModelMap map) {
        map.addAttribute("user", user);
        return ADD_USER_VIEW;
    }

    @PostMapping("/add")
    public String addUserAction(@Valid @ModelAttribute User user,
                                BindingResult result,
                                ModelMap map
    ) {

        if (result.hasErrors()) {
            System.out.println(result.getFieldErrors());
            return addUserView(user, map);
        }

        user.setUserId(UUID.randomUUID().toString());
        userService.saveUser(user);
        return "redirect:/admin/users/add";
    }

    @GetMapping
    public String viewUser(@ModelAttribute User user,
                           ModelMap map,
                           Paging paging,
                           @RequestParam(required = false) String keyword) {

        List<User> users;
        if (keyword == null) {
            users = userService.findAll(paging);
        } else {
            users = userService.searchUserByKeyword(keyword, paging);
        }
        map.addAttribute("users", users);
        map.addAttribute("paging", paging);
        map.addAttribute("keyword", keyword);
        System.out.println(keyword);
        return "/admin/user-view";
    }

    @GetMapping("/update/{userId}")
    public String updateUserView(@PathVariable String userId, ModelMap map) {
        map.addAttribute("user", userService.findOne(userId));
        map.addAttribute("is_update", true);
        return ADD_USER_VIEW;
    }

    @PostMapping("/update/{userId}")
    public String updateUserAction(User user) {
        userService.updateByUserId(user);
        return "redirect:" + VIEW_USER_URL;
    }

    @GetMapping("/delete/{userId}")
    public String deleteUserAction(@PathVariable String userId) {
        userService.deleteByUserId(userId);
        return "redirect:" + VIEW_USER_URL;
    }
}
