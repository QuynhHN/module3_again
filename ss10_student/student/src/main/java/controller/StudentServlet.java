package controller;

import model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    private List<Student> studentList = new ArrayList<>();

    public void init() {
        studentList.add(new Student(101, "Nguyễn Văn A", "1", 95.0));
        studentList.add(new Student(102, "Nguyễn Văn B", "1", 85.0));
        studentList.add(new Student(103, "Nguyễn Mỹ Linh", "0", 65.0));
        studentList.add(new Student(104, "Phạm Văn C", "1", 55.0));
        studentList.add(new Student(105, "Phạm Văn D", "1", null));
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(studentList.size()==0){
            request.setAttribute("msg","Danh sách rỗng");
        }
        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
