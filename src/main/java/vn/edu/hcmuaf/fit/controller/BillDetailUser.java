package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.Dao.BillDetailDao;
import vn.edu.hcmuaf.fit.model.BillDetails;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillDetailUser", value = "/BillDetailUser")
public class BillDetailUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<BillDetails> bd = BillDetailDao.getInstance().getById(id);
        String tp = BillDao.getInstance().totalPrice(id);
        request.setAttribute("bd", bd);
        request.setAttribute("id", id);
        request.setAttribute("tp", tp);
        request.getRequestDispatcher("billdetailuser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
