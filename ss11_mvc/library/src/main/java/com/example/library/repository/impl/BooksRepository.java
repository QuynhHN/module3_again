package com.example.library.repository.impl;

import com.example.library.model.Author;
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
//   bài 11
    private static List<Books> booksList = new ArrayList<>();
//
//    static {
//        booksList.add(new Books(1, "Toán", 45, "Nguyễn Thái Học", new Category(1, "Tự nhiên")));
//        booksList.add(new Books(2, "Văn", 34, "Trần Mình Hoàng", new Category(2, "Xã hội")));
//        booksList.add(new Books(3, "Sử", 56, "Dương Trung Quốc", new Category(2, "Xã hội")));
//        booksList.add(new Books(4, "Địa", 76, "Lê Văn Hiến", new Category(2, "Xã hội")));
//        booksList.add(new Books(5, "Hóa", 32, "Hà Văn Minh", new Category(1, "Tự nhiên")));
//
//    }

    //bai12-kết nối db
    private static final String SELECT_BY_ID = "select * from books where b_id = ?";
    private static final String INSERT_BOOK_SQL = "insert into books(b_name, b_page_size,b_author_id,b_category_id) values(?,?,?,?)";
    private static final String SELECT_BOOK_ID = "select * from books where b_id =?";
    private static final String UPDATE_BOOK_SQL = "update books set b_name = ?,b_page_size = ?,b_author_id = ?,b_category_id = ? where b_id = ?;";
    private final String DELETE_BOOK = "delete from books where b_id = ?";
    @Override
    public List<Books> findAll() {

        List<Books> bookList2 = new ArrayList<>();
        //  bai11
//        for (int i = 0; i < booksList.size(); i++) {
//
//            bookList2.add(booksList.get(i));
//
//        }
//        return bookList2;

        //bai12
        Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from books");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("b_id");
                String title = resultSet.getString("b_name");
                int pageSize = resultSet.getInt("b_page_size");
                String authorName = resultSet.getString("a_name");
                String nameCategory = resultSet.getString("c_name");
                Category category = new Category(nameCategory);
                Author author = new Author(authorName);
                bookList2.add(new Books(id, title, pageSize,author,category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
