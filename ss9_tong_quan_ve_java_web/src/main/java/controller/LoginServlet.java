package controller;

import model.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controller.LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1, "admin", "123abc"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String useName = request.getParameter("useName");
        String password = request.getParameter("password");
    // xác thực
        User userLogin = null;
        for (User user : userList) {
            if (user.getUseName().equals(useName) && user.getPasswword().equals(password)) {
                userLogin = user;
                break;
            }
        }
        if (userLogin != null) {
            //đăng nhập thành công
            response.sendRedirect("success.jsp");
        } else {
            //đăng nhập không thành công
            String messengerLogin = "Username is incorrect. Please re-enter";
            request.setAttribute("messengerLogin", messengerLogin);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
