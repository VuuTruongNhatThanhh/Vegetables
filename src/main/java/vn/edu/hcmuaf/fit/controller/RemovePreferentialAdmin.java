package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.PreferentialDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "RemovePreferentialAdmin", value = "/RemovePreferentialAdmin")
public class RemovePreferentialAdmin extends HttpServlet {
    private static  String name = "code";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        User uu = (User) request.getSession().getAttribute("auth");
        String idPr = request.getParameter("idPr");

        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());
        if(per==2) {
            response.sendRedirect("errorAccessUser.jsp");
            return;
        }
        if(per==1) {
            response.sendRedirect("AdminWeb/errorAccessAdmin.jsp");
            return;
        }



        DB.me().insert(new Log(Log.DANGER,uu.getId(),ipAddress,"Quản lý ưu đãi","Xóa mã ưu đãi: "+idPr,0));
        PreferentialDao.getInstance().delete(idPr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
