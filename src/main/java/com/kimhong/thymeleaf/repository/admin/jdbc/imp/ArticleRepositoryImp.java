package com.kimhong.thymeleaf.repository.admin.jdbc.imp;

import com.kimhong.thymeleaf.model.Article;
import com.kimhong.thymeleaf.repository.admin.jdbc.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepositoryImp implements ArticleRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Article article) {
        String sql_insert = "INSERT INTO articles" +
                "(article_id," +
                "title," +
                "description," +
                "thumbnail," +
                "author," +
                "published_date," +
                "category_id) " +
                "VALUES(" +
                "?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql_insert, ps -> {
            ps.setString(1, article.getArticleId());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getDescription());
            ps.setString(4, article.getThumbnail());
            ps.setString(5, article.getAuthor());
            ps.setDate(6, article.getPublishedDate());
            ps.setInt(7, article.getCategory().getId());
        });
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM articles where status=true order by id desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class));
    }

    @Override
    public Article find(String articleId) {
        return null;
    }

    @Override
    public int delete(String articleId) {
        return 0;
    }

    @Override
    public int update(Article newArticle) {
        return 0;
    }
}
