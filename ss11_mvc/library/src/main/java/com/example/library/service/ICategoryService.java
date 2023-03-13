package com.example.library.service;

import com.example.library.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAllCategory();
    List<Category> bookCategoryList (String categoryName);
}
