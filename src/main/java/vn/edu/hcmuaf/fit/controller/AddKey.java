package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.KeyDao;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddKey", value = "/AddKey")
public class AddKey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User uu = (User) request.getSession().getAttribute("auth");
        String publickey = request.getParameter("publickey");
        if(KeyDao.isValidDSAPublicKeyBase64(publickey)) {
            KeyDao.getInstance().addDB(uu.getId(), publickey, 1);
            String noti2 = "Nhập public key thành công";
            request.setAttribute("noti2", noti2);
            RequestDispatcher dispatcher = request.getRequestDispatcher("key.jsp");
            dispatcher.forward(request, response);
        } else {
            String err="Public key không hợp lệ";
            request.setAttribute("err", err);
            RequestDispatcher dispatcher = request.getRequestDispatcher("key.jsp");
            dispatcher.forward(request, response);
        }
    }
}
