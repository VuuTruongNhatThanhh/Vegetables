package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.Dao.BillDetailDao;
import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.model.BillDetails;
import vn.edu.hcmuaf.fit.model.Bills;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillAdmin", value = "/BillAdmin")
public class BillAdmin extends HttpServlet {
    private static  String name = "bill";
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


        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());
        if(per==2) {
            response.sendRedirect("errorAccessUser.jsp");
            return;
        }

        request.getRequestDispatcher("AdminWeb/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
