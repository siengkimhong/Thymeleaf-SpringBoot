package com.kimhong.thymeleaf.controller.admin;

import com.kimhong.thymeleaf.model.Article;
import com.kimhong.thymeleaf.model.User;
import com.kimhong.thymeleaf.service.Imp.ArticleServiceImp;
import com.kimhong.thymeleaf.service.Imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.UUID;

@Controller
@RequestMapping("/admin/articles")
public class ArticleController {
    private final String MAIN_URL = "/admin/articles";
    private final String ARTICLE_VIEW_PATH = "/admin/article";
    private CategoryServiceImp categoryService;
    private ArticleServiceImp articleService;

    @Autowired
    public ArticleController(ArticleServiceImp articleService) {
        this.articleService = articleService;
    }

    @Value("${file.server-path}")
    private String serverPath;

    @Autowired
    public void setCategoryService(CategoryServiceImp categoryService) {

        this.categoryService = categoryService;
    }

    @GetMapping
    public String articleView(ModelMap map, @ModelAttribute Article article) {
        map.addAttribute("categories", categoryService.findAll());
        map.addAttribute("article", article);
        map.addAttribute("articles", articleService.findAll());
        //System.out.println(articleService.findAll());
        return "/admin/article";
    }

    @PostMapping
    public String saveArticle(@ModelAttribute Article article,
                              @RequestParam("file") MultipartFile file) throws IOException {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User authUser = ((User) authentication.getPrincipal());

        String fileName = file.getOriginalFilename();
        String uri = UUID.randomUUID() + fileName.substring(fileName.indexOf("."));
        Files.copy(file.getInputStream(), Paths.get(serverPath + uri));
        article.setArticleId(UUID.randomUUID().toString());
        article.setAuthor(authUser.getFirstName() + " " + authUser.getLastName());
        article.setPublishedDate(new Date(System.currentTimeMillis()));
        article.setThumbnail(uri);
        articleService.save(article);
        return "redirect:" + MAIN_URL;
    }

    @PostMapping("/{articleId}/update")
    public String updateArticleById(@PathVariable String articleId,
                                    @ModelAttribute Article newArticle,
                                    @RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String uri = UUID.randomUUID() + fileName.substring(fileName.indexOf("."));
            Files.copy(file.getInputStream(), Paths.get(serverPath + uri));
            newArticle.setThumbnail(uri);
        }
        newArticle.setArticleId(articleId);
        articleService.update(newArticle);
        System.out.println("Updat Controller " + newArticle);
        return "redirect:" + MAIN_URL;
    }


    @GetMapping("/{articleId}/delete")
    public String deleteArticleById(@PathVariable String articleId) {
        articleService.delete(articleId);
        return "redirect:" + MAIN_URL;
    }
}
