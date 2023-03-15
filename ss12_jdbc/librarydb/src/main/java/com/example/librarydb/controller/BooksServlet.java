package com.example.librarydb.controller;

import com.example.librarydb.model.Author;
import com.example.librarydb.model.Books;
import com.example.librarydb.model.Category;
import com.example.librarydb.service.IBooksService;
import com.example.librarydb.service.impl.BooksService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BooksServlet", value = "/books")
public class BooksServlet extends HttpServlet {
    private IBooksService iBooksService = new BooksService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    showCreate(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit":
                try {
                    showEdit(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
//            case "delete":
//                performDelete(request, response);
//                break;
            case "search":
                performSearch(request, response);
                break;
            default:
                showBooksList(request, response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String tittleSearch = request.getParameter("tittleSearch");
        if (tittleSearch == null) {
            tittleSearch = "";
        }
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    performCreate(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit":
                try {
                    performEdit(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                deleteBook(request, response);
                break;

            default:
                showBooksList(request, response);
                break;

        }

    }
    private void performSearch(HttpServletRequest request, HttpServletResponse response) {

    }

//    private void performDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        int id = Integer.parseInt(request.getParameter("deleteId"));
//        iBooksService.delete(id);
//        response.sendRedirect("/books");
//    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Books books = iBooksService.findById(id);
        request.setAttribute("books", books);
        request.setAttribute("categoryList",iBooksService.categoryList());
        request.setAttribute("authorList",iBooksService.authorList());
        request.getRequestDispatcher("/view/edit.jsp").forward(request, response);

    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setAttribute("categoryList",iBooksService.categoryList());
        request.setAttribute("authorList",iBooksService.authorList());
        request.getRequestDispatcher("/view/create.jsp").forward(request, response);
    }

    private void showBooksList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String name = request.getParameter("name");
        try {
            request.setAttribute("nameSearch",name);
            request.setAttribute("booksList", iBooksService.findAll(name));
        } catch (java.sql.SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        request.getRequestDispatcher("/view/list.jsp").forward(request, response);
    }




    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("deleteId"));
        String mess = "Xóa Không thành công";
        boolean check = iBooksService.deleteBook(id);
        if (!check) {
            mess = "Xóa Thành công";
        }
        request.setAttribute("mess", mess);
        List<Books> booksList = null;
        try {
            booksList = iBooksService.findAll(null);
        } catch (java.sql.SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        request.setAttribute("booksList",booksList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/list.jsp");
        dispatcher.forward(request,response);
    }

    private void performEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int pageSize = Integer.parseInt(request.getParameter("pageSizeBooks"));
        int authorId=Integer.parseInt(request.getParameter("authorId"));
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        Author author= new Author(authorId);
        Category category=new Category(categoryID);
        Books books = new Books(title,pageSize,author,category);
        iBooksService.updateBooks(id, books);
        response.sendRedirect("/books");
    }

    private void performCreate(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String title = request.getParameter("titleBooks");
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int authorId=Integer.parseInt(request.getParameter("authorId"));
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        Author author= new Author(authorId);
        Category category = new Category(categoryID);
        Books books = new Books( title, pageSize, author, category);

        iBooksService.save(books);
        response.sendRedirect("/books");
    }
}
