package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.Dao.UserDao;
import vn.edu.hcmuaf.fit.Dao.permissionDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.bean.Permission;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.TypeProduct;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@WebServlet(name = "UpdatePermissAdmin", value = "/UpdatePermissAdmin")
public class UpdatePermissAdmin extends HttpServlet {
    private static  String name = "level_user";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Permission> types = permissionDao.getInstance().getAll();
        String id = request.getParameter("id");
        String u_id = request.getParameter("u_id");
        Permission p = permissionDao.getInstance().getPermissById(id);


        request.setAttribute("id", id);
        request.setAttribute("p", p);
        request.setAttribute("u_id", u_id);


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

        request.getRequestDispatcher("AdminWeb/updatePermiss.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        User uu = (User) request.getSession().getAttribute("auth");
        String id = request.getParameter("id");
        String u_id = request.getParameter("u_id");
        String per = request.getParameter("per");

        String id_rs = permissionDao.getInstance().selectidRS(id);
        String nameRs = permissionDao.getInstance().getNameRs(id_rs);

        DB.me().insert(new Log(Log.DANGER,uu.getId(),ipAddress,"Thăng / hạ quyền tài khoản","Đã thay đổi quyền tài khoản. Mã tài khoản: "+u_id+", vị trí: "+nameRs+", quyền hạn: "+permissionDao.getInstance().getNamePer(per),0));
        permissionDao.getInstance().update(id,per);
        response.sendRedirect("/LevelUpUser");
    }
}
