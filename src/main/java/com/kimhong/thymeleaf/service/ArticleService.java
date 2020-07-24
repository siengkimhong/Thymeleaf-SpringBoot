package com.kimhong.thymeleaf.service;

import com.kimhong.thymeleaf.model.Article;

import java.util.List;

public interface ArticleService {

    Article save(Article newArticle);

    List<Article> findAll();

    Article find(String articleId);

    void delete(String articleId);

    void update(Article updateArticle);
}
