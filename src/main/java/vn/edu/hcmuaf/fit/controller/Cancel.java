package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.model.Bills;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Cancel", value = "/Cancel")
public class Cancel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bills> bw = BillDao.getInstance().RecentBill();
        List<Bills> bca = BillDao.getInstance().CancelBill();
        List<Bills> bco = BillDao.getInstance().ConfirmBill();
        List<Bills> bd = BillDao.getInstance().DeliverBill();
        List<Bills> bs = BillDao.getInstance().MoveToShipper();




        request.setAttribute("bw", bw);
        request.setAttribute("bca", bca);
        request.setAttribute("bco", bco);
        request.setAttribute("bd", bd);
        request.setAttribute("bs", bs);

        request.getRequestDispatcher("AdminWeb/cancel.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
