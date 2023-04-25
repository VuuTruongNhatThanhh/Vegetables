package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.LogDao;
import vn.edu.hcmuaf.fit.Dao.UserDao;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveLogAdmin", value = "/RemoveLogAdmin")
public class RemoveLogAdmin extends HttpServlet {
    private static  String name = "log";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idU");

        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());
        if(per==2) {
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        if(per==1) {
            response.sendRedirect("/AdminWeb/errorAccessAdmin.jsp");
            return;
        }



        LogDao.getInstance().delete(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
