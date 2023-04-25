package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.model.Bills;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Revenue", value = "/Revenue")
public class Revenue extends HttpServlet {
    private static  String name = "home";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bills> bw = BillDao.getInstance().RecentBill();
        List<Bills> bca = BillDao.getInstance().CancelBill();
        List<Bills> bco = BillDao.getInstance().ConfirmBill();
        List<Bills> bd = BillDao.getInstance().DeliverBill();
        List<Bills> bs = BillDao.getInstance().MoveToShipper();

        String year = request.getParameter("year");
        if (year == null) {
            year = "2023";
        }
        int yearRange = Integer.parseInt(year);

        int inComeDay = BillDao.getInstance().totalInCome(0);
        int inComeWeek = BillDao.getInstance().totalInCome(7);
        int inComeMonth = BillDao.getInstance().totalInCome(30);
        int inComeQuarter = BillDao.getInstance().totalInCome(90);
        int inComeYear = BillDao.getInstance().totalInCome(365);

        int totalicomeJan = BillDao.getInstance().totalInComeMonth(1, yearRange);
        int totalicomeFeb = BillDao.getInstance().totalInComeMonth(2, yearRange);
        int totalicomeMarch = BillDao.getInstance().totalInComeMonth(3, yearRange);
        int totalicomeApr = BillDao.getInstance().totalInComeMonth(4, yearRange);
        int totalicomeMay = BillDao.getInstance().totalInComeMonth(5, yearRange);
        int totalicomeJun = BillDao.getInstance().totalInComeMonth(6, yearRange);
        int totalicomeJul = BillDao.getInstance().totalInComeMonth(7, yearRange);
        int totalicomeAug = BillDao.getInstance().totalInComeMonth(8, yearRange);
        int totalicomeSep = BillDao.getInstance().totalInComeMonth(9, yearRange);
        int totalicomeOc = BillDao.getInstance().totalInComeMonth(10, yearRange);
        int totalicomeNov = BillDao.getInstance().totalInComeMonth(11, yearRange);
        int totalicomeDec = BillDao.getInstance().totalInComeMonth(12, yearRange);





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

        request.setAttribute("totalinComeJan", totalicomeJan);
        request.setAttribute("totalinComeJFeb", totalicomeFeb);
        request.setAttribute("totalinComeMar", totalicomeMarch);
        request.setAttribute("totalinComeApr", totalicomeApr);
        request.setAttribute("totalinComeMay", totalicomeMay);
        request.setAttribute("totalinComeJune", totalicomeJun);
        request.setAttribute("totalinComeJul", totalicomeJul);
        request.setAttribute("totalinComeAug", totalicomeAug);
        request.setAttribute("totalinComeSep", totalicomeSep);
        request.setAttribute("totalinComeOct", totalicomeOc);
        request.setAttribute("totalinComeNov", totalicomeNov);
        request.setAttribute("totalinComeDec", totalicomeDec);
        request.setAttribute("year", yearRange);



        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());

        if(per==2) {
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }

        request.getRequestDispatcher("AdminWeb/revenue.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
