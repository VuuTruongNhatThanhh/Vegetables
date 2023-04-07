package vn.edu.hcmuaf.fit.controller;


import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
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
import java.util.*;

@WebServlet(name = "AddProductAmin", value = "/AddProductAmin")
public class AddProductAmin extends HttpServlet {
    private static  String name = "product";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypeProduct> types = TypeProductDao.getInstance().getAll();
        request.setAttribute("id", ProductDao.getInstance().createId());
        request.setAttribute("types", types);
        request.setAttribute("action", "AddProductAmin");
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

        request.getRequestDispatcher("AdminWeb/addProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String idType = request.getParameter("type");
        String dis = request.getParameter("dis");
        User uu = (User) request.getSession().getAttribute("auth");
        double discount = Integer.parseInt(dis);
        String dicription = request.getParameter("dicription_product");
        System.out.println(dicription);
        DB.me().insert(new Log(Log.WARNING,uu.getId(),ipAddress,"MANAGE PRODUCT","Thêm sản phẩm mới: "+name+", loại sản phẩm: "+ProductDao.getInstance().selectTypeName(idType)+", mô tả: "+dicription+", giảm giá: "+dis+"%" ,0));

        ProductDao.getInstance().addDB(id, name, discount, dicription, idType);
        response.sendRedirect("/ProductAdmin");



    }
}
