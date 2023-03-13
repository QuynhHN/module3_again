package com.example.library.service;

import com.example.library.model.Books;

import java.util.List;

public interface IBooksService {
    List<Books> findAll();
    void save(Books books);



}
