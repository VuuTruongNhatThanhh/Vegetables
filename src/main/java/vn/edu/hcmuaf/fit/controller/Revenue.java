package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.model.Bills;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Revenue", value = "/Revenue")
public class Revenue extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bills> bw = BillDao.getInstance().RecentBill();
        List<Bills> bca = BillDao.getInstance().CancelBill();
        List<Bills> bco = BillDao.getInstance().ConfirmBill();
        List<Bills> bd = BillDao.getInstance().DeliverBill();
        List<Bills> bs = BillDao.getInstance().MoveToShipper();

        int inComeDay = BillDao.getInstance().totalInCome(0);
        int inComeWeek = BillDao.getInstance().totalInCome(7);
        int inComeMonth = BillDao.getInstance().totalInCome(30);
        int inComeQuarter = BillDao.getInstance().totalInCome(90);
        int inComeYear = BillDao.getInstance().totalInCome(365);




        request.setAttribute("bw", bw);
        request.setAttribute("bca", bca);
        request.setAttribute("bco", bco);
        request.setAttribute("bd", bd);
        request.setAttribute("bs", bs);
        request.setAttribute("inComeDay", inComeDay);
        request.setAttribute("inComeWeek", inComeWeek);
        request.setAttribute("inComeMonth", inComeMonth);
        request.setAttribute("inComeQuarter", inComeQuarter);
        request.setAttribute("inComeYear", inComeYear);

        request.getRequestDispatcher("AdminWeb/revenue.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
