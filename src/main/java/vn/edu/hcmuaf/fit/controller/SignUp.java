package vn.edu.hcmuaf.fit.controller;


import vn.edu.hcmuaf.fit.Dao.UserDao;
import vn.edu.hcmuaf.fit.Dao.permissionDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.SHA1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;



@WebServlet(name = "SignUp", value = "/SignUp")
public class SignUp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("signUpName");
        String email = request.getParameter("signUpEmail");
        String pass = request.getParameter("signUpPass");
        String check = request.getParameter("checkPass");
        String id_u = UserDao.getInstance().getNewId();

//        generate hash code with help activation link
        String myHash;
        Random random = new Random();
        random.nextInt(999999);
        myHash = SHA1.hashPassword(""+random);


        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        System.out.println("IP address of localhost from Java Program: " + ipAddress);
        System.out.println("Name of hostname : " + hostname);







        if (uname.equals("") || uname == null || email.equals("") || email == null || pass.equals("") || pass == null || check.equals("") || check == null) {
            request.setAttribute("error", "Bạn không được để trống");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            DB.me().insert(new Log(Log.INFO,null,ipAddress,"SIGN UP","Đăng kí tài khoản thất bại do để trống thông tin",0));
//            DB.me().insert(new Log(Log.INFO,null, ipAddress, "SIGN UP",))
        } else if (UserDao.getInstance().checkEmail(email)) {
            request.setAttribute("error", "Tài khoản đã tồn tại");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            DB.me().insert(new Log(Log.INFO,null,ipAddress,"SIGN UP","Đăng kí tài khoản thất bại do tài khoản đã tồn tại",0));
        } else if (pass.equals(check)) {
            pass = SHA1.hashPassword(pass);



            String str =   UserDao.getInstance().addDB(email, pass, uname, 2, myHash);

//            UserDao.getInstance().addDB(email, pass, uname, 2, myHash);
//            response.sendRedirect("/login.jsp");
            DB.me().insert(new Log(Log.INFO,null,ipAddress,"SIGN UP","Đăng kí tài khoản thành công",0));

            if(str.equals("SUCCESS")){
                String permiss = permissionDao.getInstance().addDB("1",id_u,2);
                String permiss2 = permissionDao.getInstance().addDB("2",id_u,2);
               response.sendRedirect("verify.jsp");
            }

        } else {
            request.setAttribute("error", "Mật khẩu không trùng khớp");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            DB.me().insert(new Log(Log.INFO,null,ipAddress,"SIGN UP","Đăng kí tài khoản thất bại do mật khẩu nhập lại không trùng khớp",0));
        }


    }





}
