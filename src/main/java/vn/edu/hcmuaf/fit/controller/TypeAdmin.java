package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.TypeProduct;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TypeAdmin", value = "/TypeAdmin")
public class TypeAdmin extends HttpServlet {
    private static  String name = "type";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypeProduct> type = TypeProductDao.getInstance().getAll();
        request.setAttribute("type", type);


        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());
//        if(per>2) response.getWriter().println("Ban co toan quyen o day");
//        if(per>1) response.getWriter().println("Ban co toan quyen sua du lieu");
//        if(per>0) response.getWriter().println("Ban co toan quyen xem du lieu");
//        if(per==2) {
//            response.sendRedirect("/login.jsp");
//            return;
//        }
        if(per==2) {
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }


        request.getRequestDispatcher("AdminWeb/type.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
