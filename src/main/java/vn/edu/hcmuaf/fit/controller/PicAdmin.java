package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.PictureDao;
import vn.edu.hcmuaf.fit.model.Pictures;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PicAdmin", value = "/PicAdmin")
public class PicAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Pictures> pics = PictureDao.getInstance().getByIdProduct(id);
        request.setAttribute("id", id);
        request.setAttribute("pics", pics);
        request.getRequestDispatcher("AdminWeb/pic.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
