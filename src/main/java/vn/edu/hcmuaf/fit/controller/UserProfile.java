package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ShipmentDetailDao;
import vn.edu.hcmuaf.fit.model.ShipmentDetails;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserProfile", value = "/UserProfile")
public class UserProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        ShipmentDetails ship = ShipmentDetailDao.getInstance().get(user.getId());
        request.setAttribute("ship", ship);
        request.getRequestDispatcher("userprofile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
