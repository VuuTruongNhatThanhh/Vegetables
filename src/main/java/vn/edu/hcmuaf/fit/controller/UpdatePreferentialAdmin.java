package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.PreferentialDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.model.Preferential;
import vn.edu.hcmuaf.fit.model.TypeProduct;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdatePreferentialAdmin", value = "/UpdatePreferentialAdmin")
public class UpdatePreferentialAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
       Preferential p = PreferentialDao.getInstance().get(id);

        request.setAttribute("id", id);
        request.setAttribute("p", p);
        request.setAttribute("action", "UpdatePreferentialAdmin");
        request.setAttribute("title", "Sửa mã ưu đãi");
        request.getRequestDispatcher("AdminWeb/addPreferential.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String price = request.getParameter("price");
        String dayStart = request.getParameter("dayStart");
        String dayEnd = request.getParameter("dayEnd");

       PreferentialDao.getInstance().update(id, price, dayStart, dayEnd);
        response.sendRedirect("/PreferentialAdmin");
    }
}
