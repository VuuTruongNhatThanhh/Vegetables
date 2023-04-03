package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.WeightDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

@WebServlet(name = "RemoveWeightAdmin", value = "/RemoveWeightAdmin")
public class RemoveWeightAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        User uu = (User) request.getSession().getAttribute("auth");
        String id = request.getParameter("idW");
        DB.me().insert(new Log(Log.DANGER,uu.getId(),ipAddress,"MANAGE PRODUCT WEIGHT","Xóa khối luợng. Tên sản phẩm: "+ ProductDao.getInstance().selectWeightName(id) +", khối lượng: "+ WeightDao.getInstance().selectWeight(id)+"g",0));

        WeightDao.getInstance().delete(id);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("idW");

    }
}
