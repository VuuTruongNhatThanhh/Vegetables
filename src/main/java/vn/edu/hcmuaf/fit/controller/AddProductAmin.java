package vn.edu.hcmuaf.fit.controller;


import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.TypeProduct;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

@WebServlet(name = "AddProductAmin", value = "/AddProductAmin")
public class AddProductAmin extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypeProduct> types = TypeProductDao.getInstance().getAll();
        request.setAttribute("id", ProductDao.getInstance().createId());
        request.setAttribute("types", types);
        request.setAttribute("action", "AddProductAmin");
        request.setAttribute("title", "Thêm sản phẩm");
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
        ProductDao.getInstance().addDB(id, name, discount, dicription, idType);
        response.sendRedirect("/ProductAdmin");

        DB.me().insert(new Log(Log.WARNING,uu.getId(),ipAddress,"MANAGE PRODUCT","Thêm sản phẩm mới: "+name+", mã loại:"+idType+", mô tả: "+dicription+", giảm giá: "+dis+"%" ,0));


    }
}
