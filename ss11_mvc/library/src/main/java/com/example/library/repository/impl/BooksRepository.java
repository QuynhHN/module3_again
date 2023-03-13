package com.example.library.repository.impl;

import com.example.library.model.Books;
import com.example.library.model.Category;
import com.example.library.repository.IBooksRepository;

import java.util.ArrayList;
import java.util.List;

public class BooksRepository implements IBooksRepository {
    private static List<Books> booksList = new ArrayList<>();

    static {
        booksList.add(new Books(1, "Toán", 45, "Nguyễn Thái Học", "Tự nhiên"));
        booksList.add(new Books(2, "Văn", 34, "Trần Mình Hoàng", "Xã hội"));
        booksList.add(new Books(3, "Sử", 56, "Dương Trung Quốc", "Xã hội"));
        booksList.add(new Books(4, "Địa", 76, "Lê Văn Hiến", "Xã hội"));
        booksList.add(new Books(5, "Hóa", 32, "Hà Văn Minh", "Tự nhiên"));

    }

    @Override
    public List<Books> findAll() {
        return booksList;
    }

    @Override
    public void save(Books books) {
        booksList.add(books);
    }

    @Override
    public Books findById(int id) {
        return null;
    }

    @Override
    public void updateBooks(Books books) {

    }

    @Override
    public void deleteBooks(int id) {

    }



}