package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.LogDao;
import vn.edu.hcmuaf.fit.bean.Log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NumberAccessMod", value = "/NumberAccessMod")
public class NumberAccessMod extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> lg = LogDao.getInstance().getAll();
        String numberAccess = LogDao.getInstance().NumberAccessMod(1);
        String numberAccess2 = LogDao.getInstance().NumberAccessMod(0);
        request.setAttribute("lg", lg);
        request.setAttribute("na", numberAccess);
        request.setAttribute("na2", numberAccess2);

        request.getRequestDispatcher("AdminWeb/AccessMod.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
