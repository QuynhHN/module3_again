package com.example.library.service.impl;

import com.example.library.model.Books;
import com.example.library.repository.IBooksRepository;
import com.example.library.repository.impl.BooksRepository;
import com.example.library.service.IBooksService;

import java.util.List;

public class BooksService implements IBooksService {
    private IBooksRepository iBooksRepository = new BooksRepository();

    @Override
    public List<Books> findAll() {
        return iBooksRepository.findAll();
    }

    @Override
    public void save(Books books) {
        iBooksRepository.save(books);
    }
}
