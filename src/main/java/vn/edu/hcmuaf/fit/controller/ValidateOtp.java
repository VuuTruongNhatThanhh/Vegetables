package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ValidateOtp", value = "/ValidateOtp")
public class ValidateOtp extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        int value=Integer.parseInt(request.getParameter("otp"));
        HttpSession session=request.getSession();
        int otp=(int)session.getAttribute("otp");



        RequestDispatcher dispatcher=null;


        if (value==otp)
        {

            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");
            dispatcher=request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);
            DB.me().insert(new Log(Log.ALERT,null,ipAddress,"FORGET PASS","Khách hàng nhập OTP thành công ",0));

        }
        else
        {
            request.setAttribute("message","Sai mã OTP");

            dispatcher=request.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(request, response);
            DB.me().insert(new Log(Log.ALERT,null,ipAddress,"FORGET PASS","Khách hàng nhập sai OTP ",0));


        }

    }
}
