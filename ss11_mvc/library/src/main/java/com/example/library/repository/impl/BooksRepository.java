package com.example.library.repository.impl;

import com.example.library.model.Books;
import com.example.library.model.Category;
import com.example.library.repository.IBooksRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksRepository implements IBooksRepository {
    private static List<Books> booksList = new ArrayList<>();

    static {
        booksList.add(new Books(1, "Toán", 45, "Nguyễn Thái Học", new Category(1, "Tự nhiên")));
        booksList.add(new Books(2, "Văn", 34, "Trần Mình Hoàng", new Category(2, "Xã hội")));
        booksList.add(new Books(3, "Sử", 56, "Dương Trung Quốc", new Category(2, "Xã hội")));
        booksList.add(new Books(4, "Địa", 76, "Lê Văn Hiến", new Category(2, "Xã hội")));
        booksList.add(new Books(5, "Hóa", 32, "Hà Văn Minh", new Category(1, "Tự nhiên")));

    }

    @Override
    public List<Books> findAll() {

        List<Books> bookList2 = new ArrayList<>();

        for (int i = 0; i < booksList.size(); i++) {

            bookList2.add(booksList.get(i));

        }
        return bookList2;
    }

    @Override
    public void save(Books books) {
        booksList.add(books);
    }

    @Override
    public Books findById(int id) {
        for (Books books : booksList) {
            if (id == books.getId()) {
                return books;
            }
        }
        return null;
    }

    @Override
    public void updateBooks(int id, Books books) {
//        booksList.set(id,books);
        for (int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getId().equals(id)) {
                booksList.set(i, books);
            }
        }
    }

    @Override
    public boolean deleteBooks(int id) {
        for (int i = 0; i < booksList.size(); i++) {
            if (id == booksList.get(i).getId()) {
                id = i;
                break;
            }
        }
        booksList.remove(id);
        return false;
    }
}
