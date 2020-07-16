package com.kimhong.thymeleaf.service.Imp;

import com.kimhong.thymeleaf.model.Article;
import com.kimhong.thymeleaf.repository.admin.jdbc.imp.ArticleRepositoryImp;
import com.kimhong.thymeleaf.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {

    final private ArticleRepositoryImp articleRepository;
    @Autowired
    public ArticleServiceImp(ArticleRepositoryImp articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article save(Article newArticle) {
        if (articleRepository.save(newArticle) == 1)
            return newArticle;
        return null;
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article find(String articleId) {
        return null;
    }

    @Override
    public void delete(String articleId) {
        articleRepository.delete(articleId);
    }

    @Override
    public void update(Article updateArticle) {
        articleRepository.update(updateArticle);
    }
}
