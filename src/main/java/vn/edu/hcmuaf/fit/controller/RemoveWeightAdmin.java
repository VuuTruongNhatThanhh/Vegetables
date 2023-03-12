package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.WeightDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RemoveWeightAdmin", value = "/RemoveWeightAdmin")
public class RemoveWeightAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idW");
        WeightDao.getInstance().delete(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
