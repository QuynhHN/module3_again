package com.example.librarydb.repository.impl;

import com.example.librarydb.model.Author;
import com.example.librarydb.model.Books;
import com.example.librarydb.model.Category;
import com.example.librarydb.repository.IBooksRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksRepository implements IBooksRepository {
    private static List<Books> booksList = new ArrayList<>();
    private static final String SELECT_BY_ID = "select b_id,b_name,b_page_size,authors.a_id,authors.a_name,category.c_id,category.c_name from books\n" +
            "left join category on category.c_id = books.b_category_id\n" +
            "left join authors on authors.a_id = books.b_author_id where b_id = ?";
    private static final String INSERT_BOOK_SQL = "insert into books(b_name, b_page_size,b_author_id,b_category_id) values(?,?,?,?)";
    private static final String UPDATE_BOOK_SQL = "update books set b_name = ?,b_page_size = ?,b_author_id = ?,b_category_id = ? where b_id = ?;";
    private final String DELETE_BOOK = "delete from books where b_id = ?";

    @Override
    public List<Books> findAll(String name) {
        Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Books> bookList2 = new ArrayList<>();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement("select b_id,b_name,b_page_size,b_author_id,b_category_id,authors.a_id,authors.a_name,category.c_id, category.c_name from books\n" +
                        "left join category on category.c_id = books.b_category_id\n" +
                        "left join authors on authors.a_id = books.b_author_id where b_name like concat('%',?,'%')");
                preparedStatement.setString(1, name);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("b_id");
                    String title = resultSet.getString("b_name");
                    int pageSize = resultSet.getInt("b_page_size");
                    int authorId = resultSet.getInt("a_id");
                    String authorName = resultSet.getString("a_name");
                    int idCategory = resultSet.getInt("c_id");
                    String nameCategory = resultSet.getString("c_name");
                    Category category = new Category(idCategory, nameCategory);
                    Author author = new Author(authorId, authorName);
                    bookList2.add(new Books(id, title, pageSize, author, category));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    public Books findById(int id) throws SQLException {
        PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(SELECT_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String title = resultSet.getString("b_name");
            int pageSize = resultSet.getInt("b_page_size");
            int authorId = resultSet.getInt("a_id");
            String authorName = resultSet.getString("a_name");
            int idCategory = resultSet.getInt("c_id");
            String nameCategory = resultSet.getString("c_name");
            Category category = new Category(idCategory, nameCategory);
            Author author = new Author(authorId, authorName);
            Books books = new Books(title, pageSize, author, category);
            return books;
        }
        return null;
    }


    @Override
    public void updateBooks(int id, Books books) throws SQLException {
        Connection connection = BaseRepository.getConnection();
//        /*Bai 12*/
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement(UPDATE_BOOK_SQL);
//            preparedStatement.setString(1, books.getTitle());
//            preparedStatement.setInt(2, books.getPageSize());
//            preparedStatement.setInt(3, books.getAuthor().getAuthorId());
//            preparedStatement.setInt(4, books.getCategory().getIdCategory());
//            preparedStatement.setInt(5, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        CallableStatement callableStatement = null;
        if (connection != null) {
            try {
                callableStatement = connection.prepareCall("call update_books(?,?,?,?)");
                callableStatement.setString(1, books.getTitle());
                callableStatement.setInt(2, books.getPageSize());
                callableStatement.setInt(3, books.getAuthor().getAuthorId());
                callableStatement.setInt(4, books.getCategory().getIdCategory());
                callableStatement.setInt(5, id);
                callableStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean deleteBooks(int id) {
        Connection connection = BaseRepository.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK);
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//            return preparedStatement.executeUpdate() > 0;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return false;

        if (connection != null) {
            CallableStatement callableStatement = null;
            try {
                callableStatement = connection.prepareCall("call delete_books(?)");
                callableStatement.setInt(1, id);
                callableStatement.executeUpdate();
                return callableStatement.executeUpdate() > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public List<Author> authorList() throws SQLException {
        List<Author> authorList = new ArrayList<>();
        PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("select * from authors");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int authorId = resultSet.getInt(1);
            String authorName = resultSet.getString(2);
            Author author = new Author(authorId, authorName);
            authorList.add(author);
        }
        return authorList;
    }

    @Override
    public List<Category> categoryList() throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("select * from category");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int idCategory = resultSet.getInt(1);
            String nameCategory = resultSet.getString(2);
            Category category = new Category(idCategory, nameCategory);
            categoryList.add(category);
        }
        return categoryList;
    }
}
