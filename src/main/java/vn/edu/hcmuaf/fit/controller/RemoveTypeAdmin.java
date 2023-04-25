package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "RemoveTypeAdmin", value = "/RemoveTypeAdmin")
public class RemoveTypeAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idP = request.getParameter("idP");
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        User uu = (User) request.getSession().getAttribute("auth");
        DB.me().insert(new Log(Log.DANGER,uu.getId(),ipAddress,"Quản lý loại sản phẩm","Xóa loại sản phẩm: "+TypeProductDao.getInstance().selectTypeName(idP),0));

        TypeProductDao.getInstance().delete(idP);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
