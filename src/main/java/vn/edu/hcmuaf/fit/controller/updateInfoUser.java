package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ShipmentDetailDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "updateInfoUser", value = "/updateInfoUser")
public class updateInfoUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        String id = request.getParameter("id");
        String name = request.getParameter("username");
        String phone = request.getParameter("phone");
        String province = request.getParameter("city");
        String district = request.getParameter("dist");
        String ward = request.getParameter("ward");
        String address = request.getParameter("address");
        User uu = (User) request.getSession().getAttribute("auth");

        DB.me().insert(new Log(Log.INFO,uu.getId(),ipAddress,"Thông tin cá nhân","Cập nhật thông tin người dùng. Tên: "+name+", sđt: " +phone+", địa chỉ: "+ address+", "+ward+", "+district+", "+province,0));

        ShipmentDetailDao.getInstance().update(uu.getId(), name,phone,province,district,ward,address);
//            ShipmentDetailDao.getInstance().addDB(name,id,province,district,ward,address, uu.getId());
        response.sendRedirect("/UserProfile");

    }
}
