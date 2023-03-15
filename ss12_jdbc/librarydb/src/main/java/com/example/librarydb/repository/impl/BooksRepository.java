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
    private static final String UPDATE_BOOK_SQL = "update books set b_name = ?,b_page_size = ?,b_author_id = ?,b_category_id = ? where b_id = ?;";
    private final String DELETE_BOOK = "delete from books where b_id = ?";
    @Override
    public List<Books> findAll() {
        Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Books> bookList2 = new ArrayList<>();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement("select b_id,b_name,b_page_size,authors.a_id,authors.a_name,category.c_id, category.c_name from books\n" +
                        "left join category on category.c_id = books.b_category_id\n" +
                        "left join authors on authors.a_id = books.b_author_id;");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id= resultSet.getInt("b_id");
                    String title = resultSet.getString("b_name");
                    int pageSize = resultSet.getInt("b_page_size");
                    int authorId=resultSet.getInt("a_id");
                    String authorName = resultSet.getString("a_name");
                    int idCategory=resultSet.getInt("c_id");
                    String nameCategory = resultSet.getString("c_name");
                    Category category = new Category(idCategory,nameCategory);
                    Author author = new Author(authorId,authorName);
                    bookList2.add(new Books(id,title,pageSize,author,category));
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
            String title = resultSet.getString(1);
            int pageSize = resultSet.getInt(2);
            int authorId = resultSet.getInt(3);
            String authorName = resultSet.getString(4);
            int idCategory = resultSet.getInt(5);
            String nameCategory = resultSet.getString(6);
            Author author = new Author(authorId,authorName);
            Category category = new Category(idCategory, nameCategory);
            Books books = new Books(title, pageSize, author, category);
            return books;
        }
        return null;
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

    @Override
    public List<Author> authorList() throws SQLException {
        List<Author> authorList = new ArrayList<>();
        PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("select * from authors");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int authorID = resultSet.getInt("a_id");
            String authorName = resultSet.getString("a_name");
            Author author = new Author(authorID, authorName);
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
