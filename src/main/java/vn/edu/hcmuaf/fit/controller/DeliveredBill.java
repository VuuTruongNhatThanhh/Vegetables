package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Bills;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.List;

@WebServlet(name = "DeliveredBill", value = "/DeliveredBill")
public class DeliveredBill extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        String id = request.getParameter("id");
        String idUser = request.getParameter("idU");
        BillDao.getInstance().delivered(id);
        List<Bills> lists = BillDao.getInstance().deliveredBill(idUser);
        User uu = (User) request.getSession().getAttribute("auth");
        DB.me().insert(new Log(Log.ALERT,uu.getId(),ipAddress,"Tình trạng đơn hàng","Đã xác nhận đơn hàng đã giao. Mã đơn hàng: "+id,0));
        PrintWriter out = response.getWriter();
        for (Bills b : lists) {
            out.println("<tr id=\"" + b.getId() + "\">\n" +
                    "                                    <th scope=\"row\">" + b.getId() + "</th>\n" +
                    "                                    <td>" + b.getShip().getName() + "</td>\n" +
                    "                                    <td>" + b.getDate() + "</td>\n" +
                    "                                    <td>" + b.getShip().getPhoneNumber() + "</td>\n" +
                    "                                    <td>" + b.getTotal() + " VND</td>\n" +
                    "                                </tr>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
