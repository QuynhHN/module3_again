package com.example.librarydb.repository;

import com.example.librarydb.model.Author;
import com.example.librarydb.model.Books;
import com.example.librarydb.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface IBooksRepository {
    List<Books> findAll(String name) throws SQLException;

    void save(Books books) throws SQLException;

    Books findById(int id) throws SQLException;

    void updateBooks(int id, Books books) throws SQLException;

    boolean deleteBooks(int id);
    List<Author> authorList() throws SQLException;
    List<Category> categoryList() throws SQLException;

}
