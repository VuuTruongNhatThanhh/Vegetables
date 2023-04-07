package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.TypeProduct;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@WebServlet(name = "UpdateProductAdmin", value = "/UpdateProductAdmin")
public class UpdateProductAdmin extends HttpServlet {
    private static  String name = "product";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypeProduct> types = TypeProductDao.getInstance().getAll();
        String id = request.getParameter("id");
        String idType = request.getParameter("idType");
        Product p = ProductDao.getInstance().getProductById(id);
        request.setAttribute("id", id);
        request.setAttribute("p", p);
        request.setAttribute("idType", idType);
        request.setAttribute("types", types);
        request.setAttribute("action", "UpdateProductAdmin");
        request.setAttribute("title", "Sửa sản phẩm");

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
        ProductDao.getInstance().update(id, name, discount, dicription, idType);
        DB.me().insert(new Log(Log.WARNING,uu.getId(),ipAddress,"MANAGE PRODUCT","Sửa thông tin sản phẩm: "+name+", loại sản phẩm: "+ProductDao.getInstance().selectTypeName(idType)+", giảm giá: "+dis+", mô tả: "+dicription,0));
        response.sendRedirect("/ProductAdmin");
    }
}
