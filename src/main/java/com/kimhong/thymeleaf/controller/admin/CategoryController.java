package com.kimhong.thymeleaf.controller.admin;

import com.kimhong.thymeleaf.model.Category;
import com.kimhong.thymeleaf.service.Imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    private String CATEGORY_VIEW_NAME ="admin/category";
    private CategoryServiceImp categoryService;

    @Autowired
    public CategoryController(CategoryServiceImp categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String categoryView(@ModelAttribute("category") Category category, ModelMap map){
        map.addAttribute("category", category);
        return CATEGORY_VIEW_NAME;
    }

    @PostMapping
    public String saveCategory(@Valid @ModelAttribute("category") Category category,
                               BindingResult result,
                               RedirectAttributes redirect){
        if (result.hasErrors()){
            System.out.println("Error");
            return CATEGORY_VIEW_NAME;
        }
        System.out.println(category);
        if (categoryService.save(category) != null){
            redirect.addFlashAttribute("successful", "message");
            redirect.addFlashAttribute("true", "isSave");
        }

        return "redirect:/admin/categories";
    }
}
