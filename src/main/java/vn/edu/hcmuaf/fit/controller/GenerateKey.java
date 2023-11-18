package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.KeyDao;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "GenerateKey", value = "/GenerateKey")
public class GenerateKey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User uu = (User) request.getSession().getAttribute("auth");



        if(KeyDao.getInstance().CheckCreateKey(uu.getId())!=0){
            String noti ="Bạn đã tạo khóa rồi";
            request.setAttribute("noti", noti);
            RequestDispatcher dispatcher = request.getRequestDispatcher("key.jsp");
            dispatcher.forward(request, response);
        }else {


            try {

                String[] keyPairBase64 = KeyDao.generateKeyPairBase64();
                KeyDao.getInstance().addDB(uu.getId(), keyPairBase64[1], 1);
                String privateKey = keyPairBase64[0];


                request.setAttribute("privateKey", privateKey);

                RequestDispatcher dispatcher = request.getRequestDispatcher("privateKey.jsp");
                dispatcher.forward(request, response);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
