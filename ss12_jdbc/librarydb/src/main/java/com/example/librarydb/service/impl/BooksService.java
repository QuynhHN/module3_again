package com.example.librarydb.service.impl;

import com.example.librarydb.model.Author;
import com.example.librarydb.model.Books;
import com.example.librarydb.model.Category;
import com.example.librarydb.repository.IBooksRepository;
import com.example.librarydb.repository.impl.BooksRepository;
import com.example.librarydb.service.IBooksService;

import java.sql.SQLException;
import java.util.List;

public class BooksService implements IBooksService {
    private IBooksRepository iBooksRepository = new BooksRepository();

    @Override
    public List<Books> findAll(String name) throws SQLException {
        if(name == null){
            name = "";
        }
        return iBooksRepository.findAll(name);
    }

    @Override
    public void save(Books books) throws SQLException {
        iBooksRepository.save(books);
    }

    @Override
    public Books findById(int id) throws SQLException {
        return iBooksRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        iBooksRepository.deleteBooks(id);
    }

    @Override
    public void updateBooks(int id, Books books) throws SQLException {
        iBooksRepository.updateBooks(id, books);
    }

    @Override
    public boolean deleteBook(int id) {
        return iBooksRepository.deleteBooks(id);
    }

    @Override
    public List<Author> authorList() throws SQLException {
        return iBooksRepository.authorList();
    }

    @Override
    public List<Category> categoryList() throws SQLException {
        return iBooksRepository.categoryList();
    }
}
