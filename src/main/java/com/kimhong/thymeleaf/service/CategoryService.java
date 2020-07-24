package com.kimhong.thymeleaf.service;

import com.kimhong.thymeleaf.model.Category;

import java.util.List;

public interface CategoryService {
    Category save(Category category);

    List<Category> findAll();

    Category edit(Category category);

    Category find(int id);

    void delete(int id);
}
