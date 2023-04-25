package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.ReviewDao;
import vn.edu.hcmuaf.fit.Dao.UserDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "RemoveReviewAdmin", value = "/RemoveReviewAdmin")
public class RemoveReviewAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        User uu = (User) request.getSession().getAttribute("auth");
        String idP = request.getParameter("idP");
        String idU = request.getParameter("idU");

        DB.me().insert(new Log(Log.DANGER,uu.getId(),ipAddress,"Quản lý bình luận","Xóa bình luận của mã tài khoản: "+ idU+", sản phẩm bình luận: "+ProductDao.getInstance().selectName(idP)+", nội dung: "+ReviewDao.getInstance().selectContent(idP,idU)+", số sao: "+ReviewDao.getInstance().selectStar(idP,idU),0));
        ReviewDao.getInstance().delete(idP,idU);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
