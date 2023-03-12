package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.*;
import vn.edu.hcmuaf.fit.model.BillDetails;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.Review;
import vn.edu.hcmuaf.fit.model.Weight;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DetailControl", urlPatterns = {"/detail"})
public class DetailControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("pid");
        String idW = request.getParameter("idW");
        Product p = ProductDao.getInstance().getProductById(id);
        Weight w = WeightDao.getInstance().getWeightById(idW);
        int countAmount = BillDetailDao.getInstance().getAmount(id, idW);
        List<Review> reviews = ReviewDao.getInstance().getAll(id);
        int countStar = ReviewDao.getInstance().getStar(id);
        List<Product> lists = ProductDao.getInstance().getProductEqualsType(p.getIdType(), p.getId());
        request.setAttribute("amount", countAmount);
        request.setAttribute("detail", p);
        request.setAttribute("idW", idW);
        request.setAttribute("idP", id);
        request.setAttribute("price", w.getPrice());
        request.setAttribute("lists", lists);
        request.setAttribute("star", countStar);
        request.setAttribute("reviews", reviews);
        request.setAttribute("weight", w);
        request.getRequestDispatcher("productdetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
