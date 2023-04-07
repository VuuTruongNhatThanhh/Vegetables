package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.Dao.BillDetailDao;
import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.model.Bills;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = " StatisticalAdmin", value = "/StatisticalAdmin")
public class StatisticalAdmin extends HttpServlet {
    private static  String name = "product";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        if (date == null) {
            date = "0";
        }
        int dateRange = Integer.parseInt(date);
        int totalBill = BillDao.getInstance().totalBill(dateRange);
        int inCome = BillDao.getInstance().totalInCome(dateRange);
        int billCancelled = BillDao.getInstance().totalBillCancelled(dateRange);
        int soldOut = ProductDao.getInstance().totalProductSoldOut();
        List<Bills> bw = BillDao.getInstance().RecentBill();
        List<Product> hot = ProductDao.getInstance().getHot();
        request.setAttribute("date", dateRange);
        request.setAttribute("bw", bw);
        request.setAttribute("hot", hot);
        request.setAttribute("totalBill", totalBill);
        request.setAttribute("inCome", inCome);
        request.setAttribute("cancel", billCancelled);
        request.setAttribute("sold", soldOut);

        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());

        if(per==2) {
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }


        request.getRequestDispatcher("AdminWeb/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
