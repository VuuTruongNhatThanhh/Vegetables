package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.LogDao;
import vn.edu.hcmuaf.fit.Dao.UserDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogAdmin", value = "/LogAdmin")
public class LogAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> lg = LogDao.getInstance().getAll();
        request.setAttribute("lg", lg);
        request.getRequestDispatcher("AdminWeb/log.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
