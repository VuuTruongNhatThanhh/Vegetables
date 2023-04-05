package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.*;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Cart;
import vn.edu.hcmuaf.fit.model.ShipmentDetails;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

@WebServlet(name = "CheckOut", value = "/CheckOut")
public class CheckOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        if (user != null) {
            ShipmentDetails ship = ShipmentDetailDao.getInstance().get(user.getId());
            request.setAttribute("shipment", ship);
        }
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        String name = request.getParameter("user_mame");
        String phone = request.getParameter("phone");
        String province = request.getParameter("province");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String address = request.getParameter("address");
        User user = (User) request.getSession().getAttribute("auth");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (user == null) {
            String idNewU = UserDao.getInstance().addDB(null, null, null, 2, null);
      String idinfo =    ShipmentDetailDao.getInstance().addDB(name, phone, province, district, ward, address, idNewU);
            String idBill = BillDao.getInstance().addDB(cart.getTotal() + 15000, idNewU,idinfo);
            BillDetailDao.getInstance().addDB(cart.getListCartDetails(), idBill);
            DB.me().insert(new Log(Log.INFO,null,ipAddress,"CHECKOUT","Khách hàng đặt hàng thành công ( Khách hàng chưa đăng ký tài khoản): Tên: "+name+ ", SĐT: "+phone+", tỉnh: "+province+", quận: "+district+", phường: "+ward+", địa chỉ: " + address,0));
        } else {
            User uu = (User) request.getSession().getAttribute("auth");
            String idinfo =    ShipmentDetailDao.getInstance().addDB(name, phone, province, district, ward, address, uu.getId());
            String idBill = BillDao.getInstance().addDB(cart.getTotal() + 15000, user.getId(),idinfo);

            BillDetailDao.getInstance().addDB(cart.getListCartDetails(), idBill);
            DB.me().insert(new Log(Log.INFO,user.getId(),ipAddress,"CHECKOUT","Khách hàng đặt hàng thành công: Tên: "+name+ ", SĐT: "+phone+", tỉnh: "+province+", quận: "+district+", phường: "+ward+", địa chỉ: " + address,0));
        }
        WeightDao.getInstance().updateAmount(cart.getListCartDetails());
        request.getSession().removeAttribute("cart");
        request.getSession().removeAttribute("item");
        response.sendRedirect("/UserProfile");
    }

}
