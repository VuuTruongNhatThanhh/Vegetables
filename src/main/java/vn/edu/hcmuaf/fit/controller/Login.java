package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Cart;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.Dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    String url;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        url = request.getParameter("url");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        User user = UserDao.getInstance().checkLogin(email, pass);
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        System.out.println("IP address of localhost from Java Program: " + ipAddress);
        System.out.println("Name of hostname : " + hostname);
        if (user == null || email == null || pass == null) {
            request.setAttribute("error", "Thông tin đăng nhập không chính xác");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            DB.me().insert(new Log(Log.INFO,null,"LOGIN","Login failed: \n Email: " +email.toString(),0));
        }
        else {
            HttpSession session = request.getSession(true);
            session.setAttribute("auth", user);
            session.setMaxInactiveInterval(1800);
            if (url != null) {
                url = url.replaceAll("-", "&");
                response.sendRedirect(url);
            } else {
                if (user.getRoleId() < 2) {
                    response.sendRedirect("/StatisticalAdmin");
                    DB.me().insert(new Log(Log.ALERT,null,ipAddress,"LOGIN","Đăng nhập vào admin thành công: Email: "+email.toString(),0));
                } else {
                    response.sendRedirect("/LoadControl");
                    DB.me().insert(new Log(Log.INFO,null,ipAddress,"LOGIN","Đăng nhập vào user thành công: Email: "+email.toString(),0));
                }
            }
        }
    }
}