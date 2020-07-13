package com.kimhong.thymeleaf.service.Imp;

import com.kimhong.thymeleaf.model.Category;
import com.kimhong.thymeleaf.repository.admin.jdbc.imp.CategoryRepositoryImp;
import com.kimhong.thymeleaf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Category> findAll() {
        return categoryRepository.finAll();
    }

    @Override
    public Category edit(Category category) {
        if (find(category.getId()) != null)
            if (categoryRepository.edit(category) == 1)
                return category;
        return null;
    }

    @Override
    public Category find(int id) {
        return categoryRepository.find(id);
    }

    @Override
    public void delete(int id) {
        if (categoryRepository.find(id) != null)
             categoryRepository.delete(id);

    }
}
