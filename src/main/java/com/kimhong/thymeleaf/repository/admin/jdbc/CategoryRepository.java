package com.kimhong.thymeleaf.repository.admin.jdbc;

import com.kimhong.thymeleaf.model.Category;

import java.util.List;

public interface CategoryRepository {

    int save(Category category);

    List<Category> finAll();

    int edit(Category category);

    Category find(int id);

    int delete(int id);

}
