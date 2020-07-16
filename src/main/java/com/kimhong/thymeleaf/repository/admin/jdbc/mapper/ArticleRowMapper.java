package com.kimhong.thymeleaf.repository.admin.jdbc.mapper;

import com.kimhong.thymeleaf.model.Article;
import com.kimhong.thymeleaf.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ArticleRowMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet resultSet, int i) throws SQLException {
        Article article = new Article();
        article.setId(resultSet.getInt(1));
        article.setArticleId(resultSet.getString(2));
        article.setTitle(resultSet.getString(3));
        article.setDescription(resultSet.getString(4));
        article.setThumbnail(resultSet.getString(5));
        article.setAuthor(resultSet.getString(6));
        article.setPublishedDate(resultSet.getDate(7));
        article.setStatus(resultSet.getBoolean(8));
        article.setCategory(new Category(resultSet.getInt(9), null, true));
        return article;
    }
}
