package com.example.library.repository;

import com.example.library.model.Books;
import com.example.library.model.Category;

import java.util.List;

public interface IBooksRepository {
    List<Books> findAll();

    void save (Books books);
Books findById(int id);
void updateBooks(Books books);
void  deleteBooks(int id);
List<Category> bookCategoryList ()
}
