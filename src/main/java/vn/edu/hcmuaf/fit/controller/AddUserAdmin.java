package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.UserDao;
import vn.edu.hcmuaf.fit.Dao.permissionDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;
import vn.edu.hcmuaf.fit.services.SHA1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "AddUserAdmin", value = "/AddUserAdmin")
public class AddUserAdmin extends HttpServlet {
    private static  String name = "log";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = UserDao.getInstance().getNewId();
        request.setAttribute("id", id);

        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());
        if(per==1) {
            response.sendRedirect("/AdminWeb/errorAccessAdmin.jsp");
            return;
        }
        if(per==2) {
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }


        request.getRequestDispatcher("AdminWeb/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        String id_u = UserDao.getInstance().getNewId();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int role = Integer.parseInt(request.getParameter("role"));
        String pass = request.getParameter("pass");
        String permiss = permissionDao.getInstance().addDB("1",id_u,role);
        String permiss2 = permissionDao.getInstance().addDB("2",id_u,role);
        pass = SHA1.hashPassword(pass);
        UserDao.getInstance().addDB(email, pass, name, role,null);

        response.sendRedirect("/UserAdmin");
        User uu = (User) request.getSession().getAttribute("auth");

        DB.me().insert(new Log(Log.WARNING,uu.getId(),ipAddress,"MANAGE USER","Thêm tài khoản mới: tên: "+name+", email: "+ email+", quyền: "+role,0));

    }
}
