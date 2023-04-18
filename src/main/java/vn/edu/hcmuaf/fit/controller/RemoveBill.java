package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveBill", value = "/RemoveBill")
public class RemoveBill extends HttpServlet {
    private static  String name = "bill";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(request.getSession().getAttribute("auth")==null){
//            response.sendRedirect("/errorAccessUser.jsp");
//            return;
//        }
//        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());
//        if(per==2) {
//            response.sendRedirect("/errorAccessUser.jsp");
//            return;
//        }
//        if(per==1) {
//            response.sendRedirect("/AdminWeb/errorAccessAdmin.jsp");
//            return;
//        }
        String id = request.getParameter("id");



        BillDao.getInstance().delete(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
