package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.*;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "RemoveUserAdmin", value = "/RemoveUserAdmin")
public class RemoveUserAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        User uu = (User) request.getSession().getAttribute("auth");
        String id = request.getParameter("idU");

        permissionDao.getInstance().delete(id);
        LogDao.getInstance().deleteUser(id);
        ShipmentDetailDao.getInstance().delete(id);
//        BillDao.getInstance().deleteByUserId(id);

        DB.me().insert(new Log(Log.DANGER,uu.getId(),ipAddress,"MANAGE USER","Xóa tải khoản. Email: "+ UserDao.getInstance().selectemail(id),0));

        UserDao.getInstance().delete(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
