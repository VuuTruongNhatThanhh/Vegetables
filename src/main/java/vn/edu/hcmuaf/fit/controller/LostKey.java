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
         InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        
        User uu = (User) request.getSession().getAttribute("auth");
        DB.me().insert(new Log(Log.INFO,uu.getId(),ipAddress,"LOSTKEY",null,3));
        
        KeyDao.getInstance().updateLostkey(uu.getId());

        response.sendRedirect("key.jsp");








        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
