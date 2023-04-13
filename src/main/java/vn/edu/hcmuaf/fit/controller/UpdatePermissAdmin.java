package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.Dao.UserDao;
import vn.edu.hcmuaf.fit.Dao.permissionDao;
import vn.edu.hcmuaf.fit.bean.Permission;
import vn.edu.hcmuaf.fit.model.TypeProduct;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdatePermissAdmin", value = "/UpdatePermissAdmin")
public class UpdatePermissAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Permission> types = permissionDao.getInstance().getAll();
        String id = request.getParameter("id");
        String u_id = request.getParameter("u_id");
        Permission p = permissionDao.getInstance().getPermissById(id);


        request.setAttribute("id", id);
        request.setAttribute("p", p);
        request.setAttribute("u_id", u_id);
        request.getRequestDispatcher("AdminWeb/updatePermiss.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String per = request.getParameter("per");


        permissionDao.getInstance().update(id,per);
        response.sendRedirect("/LevelUpUser");
    }
}
