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

@WebServlet(name = "HotProductAdmin", value = "/HotProductAdmin")
public class HotProductAdmin extends HttpServlet {
    private static  String name = "home";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> hotDay = ProductDao.getInstance().getHotSelect(0);
        List<Product> hotWeek = ProductDao.getInstance().getHotSelect(7);
        List<Product> hotMonth = ProductDao.getInstance().getHotSelect(30);
        List<Product> hotQuarter = ProductDao.getInstance().getHotSelect(90);
        List<Product> hotYear = ProductDao.getInstance().getHotSelect(365);




        request.setAttribute("hotD", hotDay);
        request.setAttribute("hotW", hotWeek);
        request.setAttribute("hotM", hotMonth);
        request.setAttribute("hotQ", hotQuarter);
        request.setAttribute("hotY", hotYear);


        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());

        if(per==2) {
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }


        request.getRequestDispatcher("AdminWeb/hotproductAdmin.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
