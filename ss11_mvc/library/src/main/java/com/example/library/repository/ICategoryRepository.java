package com.example.library.repository;

import com.example.library.model.Category;

import java.util.List;

public interface ICategoryRepository {
List<Category> findAllCategory();
}
