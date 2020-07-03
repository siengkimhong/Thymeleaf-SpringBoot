package com.kimhong.thymeleaf.repository.admin.imp;

import com.kimhong.thymeleaf.model.Category;
import com.kimhong.thymeleaf.repository.admin.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImp implements CategoryRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Category category) {
        int i = 0;
        String insertSQL = "INSERT INTO categories(NAME) VALUES (?)";
        System.out.println(category.getName());
        i = jdbcTemplate.update(insertSQL, category.getName());
        return i;
    }
}
