package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.WeightDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Bills;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.List;

@WebServlet(name = "ConfirmBill", value = "/ConfirmBill")
public class ConfirmBill extends HttpServlet {
    private static  String name = "bill";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        String id = request.getParameter("id");


        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());
        if(per==2) {
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        if(per==1) {
            response.sendRedirect("/AdminWeb/errorAccessAdmin.jsp");
            return;
        }


        BillDao.getInstance().confirm(id);
        List<Bills> lists = BillDao.getInstance().ConfirmBill();
        User uu = (User) request.getSession().getAttribute("auth");





        DB.me().insert(new Log(Log.ALERT,uu.getId(),ipAddress,"Quản lý đơn hàng","Xác nhận đơn hàng. Mã đơn hàng: "+id,0));
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
