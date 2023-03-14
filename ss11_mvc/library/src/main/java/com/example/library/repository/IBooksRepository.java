package com.example.library.repository;

import com.example.library.model.Books;

import java.util.List;

public interface IBooksRepository {
    List<Books> findAll();

    void save(Books books);

    Books findById(int id);

    void updateBooks(int id, Books books);

    boolean deleteBooks(int id);
}
