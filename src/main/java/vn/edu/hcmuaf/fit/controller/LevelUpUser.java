package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.LogDao;
import vn.edu.hcmuaf.fit.Dao.permissionDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.bean.Permission;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LevelUpUser", value = "/LevelUpUser")
public class LevelUpUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Permission> lg = permissionDao.getInstance().getAll();
        request.setAttribute("lg", lg);

        request.getRequestDispatcher("AdminWeb/permiss.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
