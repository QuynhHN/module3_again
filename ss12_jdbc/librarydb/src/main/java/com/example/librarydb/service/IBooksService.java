package com.example.librarydb.service;

import com.example.librarydb.model.Author;
import com.example.librarydb.model.Books;
import com.example.librarydb.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface IBooksService {
    List<Books> findAll(String name)throws SQLException;
    void save(Books books) throws SQLException;
    Books findById(int id) throws SQLException;
    void delete(int id);
    void updateBooks(int id,Books books) throws SQLException;

    boolean deleteBook(int id);
    List<Author> authorList() throws SQLException;
    List<Category> categoryList() throws SQLException;
}
