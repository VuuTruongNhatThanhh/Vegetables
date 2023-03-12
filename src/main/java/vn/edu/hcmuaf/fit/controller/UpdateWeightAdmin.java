package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.WeightDao;
import vn.edu.hcmuaf.fit.model.Weight;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateWeightAdmin", value = "/UpdateWeightAdmin")
public class UpdateWeightAdmin extends HttpServlet {
    String url;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        url = request.getParameter("url");
        url = url.replaceAll("-", "&");
        Weight w = WeightDao.getInstance().getWeightById(id);
        request.setAttribute("id", id);
        request.setAttribute("w", w);
        request.setAttribute("title", "Sửa khối lượng");
        request.setAttribute("action", "UpdateWeightAdmin");
        request.getRequestDispatcher("AdminWeb/addWeight.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int weight = Integer.parseInt(request.getParameter("weight"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        int price = Integer.parseInt(request.getParameter("price"));
        WeightDao.getInstance().update(id, weight, amount, price);
        response.sendRedirect(url);
    }
}
