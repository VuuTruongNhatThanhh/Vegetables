package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.UserDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.SHA1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "ChangePass", value = "/ChangePass")
public class ChangePass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();        User user = (User) request.getSession().getAttribute("auth");
        String id = user.getId();
        String currentpass = request.getParameter("current_pass");
        String newpass = request.getParameter("new_pass");
        String confpass = request.getParameter("conf_pass");
        UserDao ud = new UserDao();
        User uu = (User) request.getSession().getAttribute("auth");
        currentpass = SHA1.hashPassword(currentpass);
        boolean u = ud.check(id, currentpass);
        if(!u){
            String ms="Mật khẩu cũ không đúng";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            DB.me().insert(new Log(Log.ALERT,uu.getId(),ipAddress,"CHANGE PASS","Nhập lại mật khẩu cũ không đúng",0));
        } else if(currentpass != null && newpass != null && newpass.equals(confpass)) {
            String ms="Đổi mật khẩu thành công";
            request.setAttribute("ms", ms);
            newpass = SHA1.hashPassword(newpass);
            ud.changePassword(id, newpass);
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            DB.me().insert(new Log(Log.ALERT,uu.getId(),ipAddress,"CHANGE PASS","Đổi mật khẩu thành công",0));
            response.sendRedirect("changepassword.jsp");
        }else {
            String ms="Mật khẩu không trùng khớp";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            DB.me().insert(new Log(Log.ALERT,uu.getId(),ipAddress,"CHANGE PASS","Mật khẩu nhập lại không trùng khớp",0));
        }
    }
}
