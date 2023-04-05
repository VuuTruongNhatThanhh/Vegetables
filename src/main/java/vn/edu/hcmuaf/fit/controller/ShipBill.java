package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.model.Bills;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShipBill", value = "/ShipBill")
public class ShipBill extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        BillDao.getInstance().ship(id);
        List<Bills> lists = BillDao.getInstance().ShipBill();
        PrintWriter out = response.getWriter();
        for (Bills b : lists) {
            out.println("<tr id=\"" + b.getId() + "\">\n" +
                    "                                    <th scope=\"row\">" + b.getId() + "</th>\n" +
                    "                                    <td>" + b.getShip().getName() + "</td>\n" +
                    "                                    <td>" + b.getDate() + "</td>\n" +
                    "                                    <td>" + b.getShip().getPhoneNumber() + "</td>\n" +
                    "                                    <td>" + b.getShip().getFullAddress() + "</td>\n" +
                    "                                    <td>" + b.getTotal() + " VND</td>\n" +
                    "                                    <td>\n" +
                    "                                        <button onclick=\"remove('" + b.getId() + "')\" class=\"btn btn-primary btn-sm trash\"\n" +
                    "                                                type=\"button\" title=\"Xóa\">\n" +
                    "                                            <i class=\"fas fa-trash-alt\"></i>\n" +
                    "                                        </button>\n" +
                    "                                    </td>\n" +
                    "                                </tr>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
