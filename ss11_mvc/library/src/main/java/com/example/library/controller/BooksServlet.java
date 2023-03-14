package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.Category;
import com.example.library.service.IBooksService;
import com.example.library.service.ICategoryService;
import com.example.library.service.impl.BooksService;
import com.example.library.service.impl.CategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BooksServlet", value = "/books")
public class BooksServlet extends HttpServlet {
    private IBooksService iBooksService = new BooksService();
    private ICategoryService iCategoryService = new CategoryService();

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
                showEdit(request, response);
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

    private void performSearch(HttpServletRequest request, HttpServletResponse response) {

    }

//    private void performDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        int id = Integer.parseInt(request.getParameter("deleteId"));
//        iBooksService.delete(id);
//        response.sendRedirect("/books");
//    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Books books = iBooksService.findById(id);
        request.setAttribute("books", books);
        request.setAttribute("categoryList", iCategoryService.bookCategoryList());
        request.getRequestDispatcher("/view/edit.jsp").forward(request, response);

    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("categoryList", iCategoryService.bookCategoryList());
        request.getRequestDispatcher("/view/create.jsp").forward(request, response);
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
        String tittleSearch = request.getParameter("tittleSearch");
        if (tittleSearch == null) {
            tittleSearch = "";
        }
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                performCreate(request, response);
                break;
            case "edit":
                performEdit(request, response);
                break;
            case "delete":
                deleteBook(request, response);
                break;

            default:
                showBooksList(request, response);
                break;

        }

    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String mess = "Xóa Không thành công";
        boolean check = iBooksService.deleteBook(id);
        if (!check) {
            mess = "Xóa Thành công";
        }
        request.setAttribute("mess", mess);
        List<Books> booksList = iBooksService.findAll();
        request.setAttribute("playList",booksList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/list.jsp");
        dispatcher.forward(request,response);
    }

    private void performEdit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int pageSize = Integer.parseInt(request.getParameter("pageSizeBooks"));
        String author = request.getParameter("author");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        
        Category categorySel = null;
        List<Category> categoryList= new ArrayList<>();
        categoryList = iCategoryService.findAllCategory();
        for(Category category: categoryList){
            if(category.getIdCategory().equals(categoryID)){
                categorySel = category;
                break;
            }
        }
        Books books = new Books(id, title, pageSize, author);
        books.setCategory(categorySel);
        iBooksService.updateBooks(id, books);
    }

    private void performCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idBooks"));
        String title = request.getParameter("titleBooks");
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String author = request.getParameter("author");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        Category category = new Category(categoryID);
        Books books = new Books(id, title, pageSize, author, category);

        iBooksService.save(books);
        response.sendRedirect("/books");
    }
}
