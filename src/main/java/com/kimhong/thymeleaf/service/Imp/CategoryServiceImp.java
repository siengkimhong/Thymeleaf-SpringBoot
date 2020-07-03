package com.kimhong.thymeleaf.service.Imp;

import com.kimhong.thymeleaf.model.Category;
import com.kimhong.thymeleaf.repository.admin.imp.CategoryRepositoryImp;
import com.kimhong.thymeleaf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements CategoryService {

    final private CategoryRepositoryImp categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepositoryImp categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        if (categoryRepository.save(category) == 1)
            return category;
        return null;
    }
}
