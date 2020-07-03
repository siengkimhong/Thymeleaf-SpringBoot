package com.kimhong.thymeleaf.controller.admin;

import com.kimhong.thymeleaf.model.Category;
import com.kimhong.thymeleaf.service.Imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

        map.addAttribute("categoryList", categoryService.findAll());
        map.addAttribute("category", category);
        return CATEGORY_VIEW_NAME;
    }

    @PostMapping
    public String saveCategory(@Valid @ModelAttribute("category") Category category,
                               BindingResult result,
                               RedirectAttributes redirect,
                               ModelMap map){
        if (result.hasErrors()){
            return categoryView(category, map);
        }
        if (categoryService.save(category) != null){
            redirect.addFlashAttribute("successful", "message");
            redirect.addFlashAttribute("true", "isSave");
        }

        return "redirect:/admin/categories";
    }

    @PostMapping("/{id}")
    public String editCategory(@Valid @ModelAttribute("category") Category category,
                               RedirectAttributes redirect){

        if (categoryService.edit(category) != null)
        {
            redirect.addFlashAttribute("successful", "message");
            redirect.addFlashAttribute("true", "isSave");
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable int id){
        return "under testing";
    }
}
