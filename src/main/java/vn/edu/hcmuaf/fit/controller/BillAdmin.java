package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.Dao.BillDetailDao;
import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.model.BillDetails;
import vn.edu.hcmuaf.fit.model.Bills;
import vn.edu.hcmuaf.fit.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillAdmin", value = "/BillAdmin")
public class BillAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bills> bw = BillDao.getInstance().RecentBill();
        List<Bills> bca = BillDao.getInstance().CancelBill();
        List<Bills> bco = BillDao.getInstance().ConfirmBill();
        request.setAttribute("bw", bw);
        request.setAttribute("bca", bca);
        request.setAttribute("bco", bco);
        request.getRequestDispatcher("AdminWeb/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
