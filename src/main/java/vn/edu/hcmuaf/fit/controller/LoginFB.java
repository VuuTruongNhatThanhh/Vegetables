package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginFB", value = "/LoginFB")
public class LoginFB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String unamefb = request.getParameter("user_name");


        UserDao.getInstance().addDB(null,null,unamefb,2,null);
        response.sendRedirect("login.jsp");


    }
}
