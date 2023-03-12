package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.WeightDao;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.Weight;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WeightAdmin", value = "/WeightAdmin")
public class WeightAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Weight> weights = WeightDao.getInstance().getByIdProduct(id);
        Product p = ProductDao.getInstance().getProductById(id);
        request.setAttribute("id", id);
        request.setAttribute("title", p.getName());
        request.setAttribute("weights", weights);
        request.getRequestDispatcher("AdminWeb/weightProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
