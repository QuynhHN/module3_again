package com.example.library.service;

import com.example.library.model.Books;

import java.util.List;

public interface IBooksService {
    List<Books> findAll();
    void save(Books books);
    Books findById(int id);
    void delete(int id);
//    List<Category> bookCategoryList (String categoryName);



}
