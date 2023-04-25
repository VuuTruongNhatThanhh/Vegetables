package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.Dao.UserDao;
import vn.edu.hcmuaf.fit.bean.Log;
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

@WebServlet(name = "AddTypeAdmin", value = "/AddTypeAdmin")
public class AddTypeAdmin extends HttpServlet {
    private static  String name = "type";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = TypeProductDao.getInstance().getNewId();
        request.setAttribute("id", id);
//        request.setAttribute("types", types);
        request.setAttribute("action", "AddTypeAdmin");
        request.setAttribute("title", "Thêm sản phẩm");



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


        request.getRequestDispatcher("AdminWeb/addType.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        User uu = (User) request.getSession().getAttribute("auth");
        String name = request.getParameter("name");
        String typeFather = request.getParameter("typeFather");

        DB.me().insert(new Log(Log.WARNING,uu.getId(),ipAddress,"Quản lý loại sản phẩm","Thêm loại sản phẩm mới: "+name+", phân loại cha: " +TypeProductDao.getInstance().selectTypeName(typeFather),0));        String id_u = TypeProductDao.getInstance().getNewId();


        TypeProductDao.getInstance().addDB( name, typeFather);

        response.sendRedirect("/TypeAdmin");
    }
}
