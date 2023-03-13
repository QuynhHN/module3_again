package com.example.library.controller;

import com.example.library.model.Books;
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
//                showEdit(request,response);
                break;
            case "delete":
                performDelete (request, response);
                break;
            case "search":
                performSearchUser (request, response);
                break;
            default:
                showBooksList(request, response);
                break;
        }
    }

    private void performSearchUser(HttpServletRequest request, HttpServletResponse response) {

    }

    private void performDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id= Integer.parseInt(request.getParameter("deleteId"));
        iBooksService.delete(id);
        response.sendRedirect("/books");
    }

//    private void showEdit(HttpServletRequest request, HttpServletResponse response) {
//        int id= Integer.parseInt(request.getParameter("idBooks"));
//        Books books=iBooksService.findById(id);
//        request.setAttribute("books",books);
//
//    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        int id= Integer.parseInt(request.getParameter(""));
//        Books books=iBooksService.findById(id);
//        request.setAttribute("category",iBooksService.bookCategoryList(books.getCategory()));
       request.getRequestDispatcher("/view/create.jsp").forward(request,response);
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
        String tittleSearch=request.getParameter("tittleSearch");
        if (tittleSearch==null){
            tittleSearch = "";
        }
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
        Books books = new Books(id, title, pageSize, author, category);
        iBooksService.save(books);
        response.sendRedirect("/books");
    }
}
