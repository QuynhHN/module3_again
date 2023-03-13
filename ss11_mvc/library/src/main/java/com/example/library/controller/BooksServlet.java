package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.Category;
import com.example.library.service.IBooksService;
import com.example.library.service.impl.BooksService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
                showCreate(request, response);
                break;
            case "edit":
                showEdit(request,response);
                break;
            default:
                showBooksList(request, response);
                break;
        }
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("categoryList",iBooksService.categoryList2());
        response.sendRedirect("/view/create.jsp");
    }

    private void showBooksList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("booksList", iBooksService.findAll());
        request.getRequestDispatcher("/view/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                performCreate (request, response);
                break;
            case "edit":
                performEdit(request,response);
            default:
                showBooksList(request, response);
                break;

        }

    }

    private void performEdit(HttpServletRequest request, HttpServletResponse response) {
    }

    private void performCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idBooks"));
        String title = request.getParameter("titleBooks");
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        switch (category) {
            case "Tự nhiên":
                category = "1";
                break;
            case "Xã hội":
                category = "2";
                break;
            case "Truyện":
                category = "3";
                break;
            case "Tiểu thuyết":
                category = "4";
                break;
            case "Khác":
                category = "5";
                break;
        }
        Books books = new Books(id, title, pageSize, author, category);
        iBooksService.save(books);
        response.sendRedirect("/books");
    }
}
