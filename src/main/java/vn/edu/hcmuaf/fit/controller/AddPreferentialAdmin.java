package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.PreferentialDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddPreferentialAdmin", value = "/AddPreferentialAdmin")
public class AddPreferentialAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setAttribute("action", "AddPreferentialAdmin");
        request.setAttribute("title", "Thêm mã ưu đãi");

        request.getRequestDispatcher("AdminWeb/addPreferential.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String price = request.getParameter("price");
        String dayStart = request.getParameter("dayStart");
        String dayEnd = request.getParameter("dayEnd");


        PreferentialDao.getInstance().addDB(id, price, dayStart, dayEnd);

        response.sendRedirect("/PreferentialAdmin");
    }
}
