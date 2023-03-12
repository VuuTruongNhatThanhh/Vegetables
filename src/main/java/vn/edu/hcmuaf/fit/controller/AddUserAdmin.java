package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddUserAdmin", value = "/AddUserAdmin")
public class AddUserAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = UserDao.getInstance().getNewId();
        request.setAttribute("id", id);
        request.getRequestDispatcher("AdminWeb/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int role = Integer.parseInt(request.getParameter("role"));
        String pass = request.getParameter("pass");
        UserDao.getInstance().addDB(email, pass, name, role,null);
        response.sendRedirect("/UserAdmin");
    }
}
