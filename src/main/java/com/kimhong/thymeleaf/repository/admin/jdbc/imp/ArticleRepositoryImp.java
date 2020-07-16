package com.kimhong.thymeleaf.repository.admin.jdbc.imp;

import com.kimhong.thymeleaf.model.Article;
import com.kimhong.thymeleaf.repository.admin.jdbc.ArticleRepository;
import com.kimhong.thymeleaf.repository.admin.jdbc.mapper.ArticleRowMapper;
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
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    @Override
    public Article find(String articleId) {
        return null;
    }

    @Override
    public int delete(String articleId) {
        String SQL = "UPDATE articles set status=false where article_id=?";
        return jdbcTemplate.update(SQL, articleId);
    }

    @Override
    public int update(Article newArticle) {
        String SQL = "UPDATE articles set title=?, description=?, thumbnail=?, category_id=? where article_id=?";
        return jdbcTemplate.update(SQL,
                newArticle.getTitle(),
                newArticle.getDescription(),
                newArticle.getThumbnail(),
                newArticle.getCategory().getId(),
                newArticle.getArticleId());
    }
}
