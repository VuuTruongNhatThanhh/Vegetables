package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.KeyDao;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "LostKey", value = "/LostKey")
public class LostKey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User uu = (User) request.getSession().getAttribute("auth");
        KeyDao.getInstance().updateLostkey(uu.getId());

        response.sendRedirect("key.jsp");








        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
