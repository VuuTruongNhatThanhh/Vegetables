package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.model.Bills;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OderStatus", value = "/OderStatus")
public class OderStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        List<Bills> bw = BillDao.getInstance().RecentBill(user.getId());
        List<Bills> bco = BillDao.getInstance().ConfirmBill(user.getId());
        List<Bills> bd = BillDao.getInstance().deliveredBill(user.getId());
        List<Bills> bca = BillDao.getInstance().CancelBill(user.getId());
        List<Bills> bs = BillDao.getInstance().ShipBill(user.getId());
        request.setAttribute("bw", bw);
        request.setAttribute("bco", bco);
        request.setAttribute("bd", bd);
        request.setAttribute("bca", bca);
        request.setAttribute("bs", bs);
        request.getRequestDispatcher("orderstatus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
