package com.example.library.service.impl;

import com.example.library.model.Category;
import com.example.library.repository.ICategoryRepository;
import com.example.library.repository.impl.CategoryRepository;
import com.example.library.service.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {
    private ICategoryRepository iCategoryRepository= new CategoryRepository();
        @Override
        public List<Category> findAll() {
            return iCategoryRepository.findAllCategory();
        }
}
