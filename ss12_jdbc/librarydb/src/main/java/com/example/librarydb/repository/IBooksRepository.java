package com.example.librarydb.repository;

import com.example.librarydb.model.Books;

import java.sql.SQLException;
import java.util.List;

public interface IBooksRepository {
    List<Books> findAll();

    void save(Books books) throws SQLException;

    Books findById(int id);

    void updateBooks(int id, Books books);

    boolean deleteBooks(int id);
}
