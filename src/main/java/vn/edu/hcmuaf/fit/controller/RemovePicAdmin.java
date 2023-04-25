package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.PictureDao;
import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "RemovePicAdmin", value = "/RemovePicAdmin")
public class RemovePicAdmin extends HttpServlet {
    private static  String name = "product";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        User uu = (User) request.getSession().getAttribute("auth");
        int id = Integer.parseInt(request.getParameter("idP"));

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

        DB.me().insert(new Log(Log.DANGER,uu.getId(),ipAddress,"Quản lý sản phẩm","Xóa hình sản phẩm. Tên sản phẩm: "+ ProductDao.getInstance().selectImageName(id),0));

        PictureDao.getInstance().delete(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
