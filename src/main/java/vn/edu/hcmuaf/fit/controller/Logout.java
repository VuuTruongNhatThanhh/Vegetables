package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

import static java.lang.System.out;

@WebServlet(name = "Logout", value = "/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            InetAddress addr = InetAddress.getLocalHost();

            //Host IP Address
            String ipAddress = addr.getHostAddress();
            //Hostname
            String hostname = addr.getHostName();
            HttpSession session = request.getSession();
            User u = (User) request.getSession().getAttribute("auth");
            DB.me().insert(new Log(Log.INFO,u.getId(),ipAddress,"LOGOUT",null,0));
            session.removeAttribute("auth");
            session.removeAttribute("cart");
            session.removeAttribute("items");

            response.sendRedirect("/LoadControl");

        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
