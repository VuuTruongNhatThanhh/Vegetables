package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.PreferentialDao;
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

@WebServlet(name = "AddPreferentialAdmin", value = "/AddPreferentialAdmin")
public class AddPreferentialAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setAttribute("action", "AddPreferentialAdmin");
        request.setAttribute("title", "Thêm mã ưu đãi");

        request.getRequestDispatcher("AdminWeb/addPreferential.jsp").forward(request, response);
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
        String price = request.getParameter("price");
        String dayStart = request.getParameter("dayStart");
        String dayEnd = request.getParameter("dayEnd");

        DB.me().insert(new Log(Log.WARNING,uu.getId(),ipAddress,"Quản lý ưu đãi","Thêm mã ưu đãi mới: "+id+", giá tiền được giảm: "+ price+", ngày bắt đầu: "+dayStart+", ngày kết thúc: "+dayEnd ,0));
        PreferentialDao.getInstance().addDB(id, price, dayStart, dayEnd);

        response.sendRedirect("/PreferentialAdmin");
    }
}
