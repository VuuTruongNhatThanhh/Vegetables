package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.WeightDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "AddWeightAdmin", value = "/AddWeightAdmin")
public class AddWeightAdmin extends HttpServlet {
    private static  String name = "product";
    String url, idP;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        url = request.getParameter("url");
        url = url.replaceAll("-", "&");
        idP = request.getParameter("id");
        request.setAttribute("url", url);
        request.setAttribute("id", WeightDao.getInstance().getNewId());
        request.setAttribute("title", "Thêm khối lượng");
        request.setAttribute("action", "AddWeightAdmin");

        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());
        if(per==2) {
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }

        request.getRequestDispatcher("AdminWeb/addWeight.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        User uu = (User) request.getSession().getAttribute("auth");
//        idP = request.getParameter("id");
        String id = request.getParameter("id");

        int weight = Integer.parseInt(request.getParameter("weight"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        int price = Integer.parseInt(request.getParameter("price"));
        WeightDao.getInstance().addDB(id, weight, amount, price, idP);
        response.sendRedirect(url);
        DB.me().insert(new Log(Log.WARNING,uu.getId(),ipAddress,"MANAGE PRODUCT WEIGHT","Thêm khối luợng mới. Tên sản phẩm: "+ ProductDao.getInstance().selectName(idP) +", khối lượng: "+ weight+"g, số lượng: "+amount+", giá tiền: "+price ,0));
    }
}
