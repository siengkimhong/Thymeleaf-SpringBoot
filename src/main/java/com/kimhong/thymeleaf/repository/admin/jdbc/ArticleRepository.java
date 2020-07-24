package com.kimhong.thymeleaf.repository.admin.jdbc;


import com.kimhong.thymeleaf.model.Article;

import java.util.List;

public interface ArticleRepository {

    int save(Article article);

    List<Article> findAll();

    Article find(String articleId);

    int delete(String articleId);

    int update(Article newArticle);
}
