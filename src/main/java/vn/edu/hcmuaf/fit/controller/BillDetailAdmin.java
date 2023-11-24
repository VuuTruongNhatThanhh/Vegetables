package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.Dao.BillDetailDao;
import vn.edu.hcmuaf.fit.Dao.TransportDao;
import vn.edu.hcmuaf.fit.model.BillDetails;
import vn.edu.hcmuaf.fit.model.Bills;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillDetail", value = "/BillDetailAdmin")
public class BillDetailAdmin extends HttpServlet {
    private static  String name = "product";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<BillDetails> bd = BillDetailDao.getInstance().getById(id);
        String tp = BillDao.getInstance().totalPrice(id);
        String fee = BillDao.getInstance().getFee(id);
//        String idTrans = TransportDao.getInstance().selectIdTransport(id);
//        String createAt = TransportDao.getInstance().createdAtTransport(id);
//        String leadtime = TransportDao.getInstance().leaTimeTransport(id);
        String name = BillDao.getInstance().getName(id);
        String phone = BillDao.getInstance().getPhone(id);
        String province = BillDao.getInstance().getProvince(id);
        String district = BillDao.getInstance().getDistrict(id);
        String ward = BillDao.getInstance().getWard(id);
        String address = BillDao.getInstance().getAddress(id);
        boolean verify = false;
        try {
            verify = BillDetails.verify(id);

        } catch (Exception e) {
            verify = false;
        }

        String hash = BillDetails.gethash(id);

        String status = BillDetails.getstatus(id);


        request.setAttribute("bd", bd);
        request.setAttribute("id", id);
        request.setAttribute("tp", tp);
        request.setAttribute("fee", fee);
//        request.setAttribute("idTrans", idTrans);
//        request.setAttribute("createAt", createAt);
//        request.setAttribute("leadtime", leadtime);
        request.setAttribute("name", name);
        request.setAttribute("phone", phone);
        request.setAttribute("province", province);
        request.setAttribute("district", district);
        request.setAttribute("ward", ward);
        request.setAttribute("address", address);
        request.setAttribute("verify", verify);
        request.setAttribute("hash", hash);
        request.setAttribute("status", status);


        if (request.getSession().getAttribute("auth") == null) {
            response.sendRedirect("errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User) (request.getSession().getAttribute("auth"))).getId());
        if (per == 2) {
            response.sendRedirect("errorAccessUser.jsp");
            return;
        }

        request.getRequestDispatcher("AdminWeb/billdetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
