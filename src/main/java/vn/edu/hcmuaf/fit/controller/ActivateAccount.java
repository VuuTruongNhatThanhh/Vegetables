package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.database.DBConnect;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "ActivateAccount", value = "/ActivateAccount")
public class ActivateAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("key1");
        String hash = request.getParameter("key2");
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select email, hash, activate from user where email=? and hash=? AND activate='0'");
            ps.setString(1, email);
            ps.setString(2, hash);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PreparedStatement pst1 = DBConnect.getInstance().get("update user set activate='1' where email=? and hash=? AND DATEDIFF(Date(NOW()), date_add_hash)<1");
                pst1.setString(1, email);
                pst1.setString(2, hash);
                int i = pst1.executeUpdate();
                if (i == 1) {
                    response.sendRedirect("login.jsp");
                } else {
                    response.sendRedirect("signup.jsp");
                }

            }
        } catch (Exception ex) {
            System.out.println("ActivateAccount File"+ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
