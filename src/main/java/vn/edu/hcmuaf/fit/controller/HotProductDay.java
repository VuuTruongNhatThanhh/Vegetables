package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HotProductDay", value = "/HotProductDay")
public class HotProductDay extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> hotD = ProductDao.getInstance().getHotSelect(0);
        request.setAttribute("hotD", hotD);

        request.getRequestDispatcher("AdminWeb/hotproductDay.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
