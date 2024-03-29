package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HotProductWeek", value = "/HotProductWeek")
public class HotProductWeek extends HttpServlet {
    private static  String name = "home";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> hotW = ProductDao.getInstance().getHotSelect(7);
        request.setAttribute("hotW", hotW);



        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());

        if(per==2) {
            response.sendRedirect("errorAccessUser.jsp");
            return;
        }


        request.getRequestDispatcher("AdminWeb/hotproductWeek.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
