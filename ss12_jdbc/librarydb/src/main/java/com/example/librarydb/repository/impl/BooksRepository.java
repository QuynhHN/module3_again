package com.example.librarydb.repository.impl;

import com.example.librarydb.model.Author;
import com.example.librarydb.model.Books;
import com.example.librarydb.model.Category;
import com.example.librarydb.repository.IBooksRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksRepository implements IBooksRepository {
    private static List<Books> booksList = new ArrayList<>();
    private static final String SELECT_BY_ID = "select * from books where b_id = ?";
    private static final String INSERT_BOOK_SQL = "insert into books(b_name, b_page_size,b_author_id,b_category_id) values(?,?,?,?)";
    private static final String SELECT_BOOK_ID = "select * from books where b_id =?";
    private static final String UPDATE_BOOK_SQL = "update books set b_name = ?,b_page_size = ?,b_author_id = ?,b_category_id = ? where b_id = ?;";
    private final String DELETE_BOOK = "delete from books where b_id = ?";
    @Override
    public List<Books> findAll() {
        List<Books> bookList2 = new ArrayList<>();
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
    public void save(Books books) throws SQLException {
        Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK_SQL);
        preparedStatement.setString(1, books.getTitle());
        preparedStatement.setInt(2, books.getPageSize());
        preparedStatement.setInt(3, books.getAuthor().getAuthorId());
        preparedStatement.setInt(4, books.getCategory().getIdCategory());
        preparedStatement.executeUpdate();
    }


    @Override
    public Books findById(int id) {

    }

    @Override
    public void updateBooks(int id, Books books) {
        Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BOOK_SQL);
            preparedStatement.setString(1, books.getTitle());
            preparedStatement.setInt(2, books.getPageSize());
            preparedStatement.setInt(3, books.getAuthor().getAuthorId());
            preparedStatement.setInt(4, books.getCategory().getIdCategory());
            preparedStatement.setInt(5, books.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteBooks(int id) {
        Connection connection = BaseRepository.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
