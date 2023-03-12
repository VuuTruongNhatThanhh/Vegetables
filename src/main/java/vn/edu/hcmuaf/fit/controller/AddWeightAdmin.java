package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.WeightDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddWeightAdmin", value = "/AddWeightAdmin")
public class AddWeightAdmin extends HttpServlet {
    String url, idP;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        url = request.getParameter("url");
        url = url.replaceAll("-", "&");
        idP = request.getParameter("id");
        request.setAttribute("url", url);
        request.setAttribute("id", WeightDao.getInstance().getNewId());
        request.setAttribute("title", "Thêm khối lượng");
        request.setAttribute("action", "AddWeightAdmin");
        request.getRequestDispatcher("AdminWeb/addWeight.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int weight = Integer.parseInt(request.getParameter("weight"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        int price = Integer.parseInt(request.getParameter("price"));
        WeightDao.getInstance().addDB(id, weight, amount, price, idP);
        response.sendRedirect(url);
    }
}
