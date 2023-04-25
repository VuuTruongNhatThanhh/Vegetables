package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.PreferentialDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "RemovePreferentialAdmin", value = "/RemovePreferentialAdmin")
public class RemovePreferentialAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        User uu = (User) request.getSession().getAttribute("auth");
        String idPr = request.getParameter("idPr");

        DB.me().insert(new Log(Log.DANGER,uu.getId(),ipAddress,"Quản lý ưu đãi","Xóa mã ưu đãi: "+idPr,0));
        PreferentialDao.getInstance().delete(idPr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
