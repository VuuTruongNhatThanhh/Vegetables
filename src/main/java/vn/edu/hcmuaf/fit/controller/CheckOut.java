package vn.edu.hcmuaf.fit.controller;

import org.json.JSONException;
import vn.edu.hcmuaf.fit.Dao.*;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Cart;
import vn.edu.hcmuaf.fit.model.ShipmentDetails;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.API_LOGISTIC.Login_API;
import vn.edu.hcmuaf.fit.services.API_LOGISTIC.Province;
import vn.edu.hcmuaf.fit.services.API_LOGISTIC.Province_API;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.List;

@WebServlet(name = "CheckOut", value = "/CheckOut")
public class CheckOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");

        //API LOGISTIC
        HttpSession session = request.getSession();
        Login_API login_api = new Login_API();
        String API_KEY = null;
        try {
            API_KEY = login_api.login();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("parameterName", API_KEY);

        if (user != null) {
            ShipmentDetails ship = ShipmentDetailDao.getInstance().get(user.getId());
            request.setAttribute("shipment", ship);

            List<Province> provinces = Province_API.convert(API_KEY);
            request.setAttribute("listProvinces", provinces);


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
        String province = request.getParameter("province-value");
        String district = request.getParameter("district-value");
        String ward = request.getParameter("ward-value");
        String provinceID = request.getParameter("province");
        String districtID = request.getParameter("district");
        String wardID = request.getParameter("ward");
        String address = request.getParameter("address");
        String shippingFee = request.getParameter("shippingFee");
        int fee = Integer.parseInt(shippingFee);
        User user = (User) request.getSession().getAttribute("auth");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (user == null) {
            String idNewU = UserDao.getInstance().addDB(null, null, null, 2, null);
      String idinfo =    ShipmentDetailDao.getInstance().addDB(name, phone, province, district, ward, address, idNewU,provinceID,districtID,wardID);
            String idBill = BillDao.getInstance().addDB(cart.getTotal() + fee, idNewU,idinfo, fee);
            BillDetailDao.getInstance().addDB(cart.getListCartDetails(), idBill);
            DB.me().insert(new Log(Log.INFO,null,ipAddress,"Thanh toán","Khách hàng đặt hàng thành công ( Khách hàng chưa đăng ký tài khoản): Tên: "+name+ ", SĐT: "+phone+", tỉnh: "+province+", quận: "+district+", phường: "+ward+", địa chỉ: " + address,0));
        } else {
            User uu = (User) request.getSession().getAttribute("auth");
            String idinfo =    ShipmentDetailDao.getInstance().addDB(name, phone, province, district, ward, address, uu.getId(),provinceID,districtID,wardID);
            String idBill = BillDao.getInstance().addDB(cart.getTotal() + fee, user.getId(),idinfo,fee);

            BillDetailDao.getInstance().addDB(cart.getListCartDetails(), idBill);
            DB.me().insert(new Log(Log.INFO,user.getId(),ipAddress,"Thanh toán","Khách hàng đặt hàng thành công: Tên: "+name+ ", SĐT: "+phone+", tỉnh: "+province+", quận: "+district+", phường: "+ward+", địa chỉ: " + address,0));
        }
        WeightDao.getInstance().updateAmount(cart.getListCartDetails());
        request.getSession().removeAttribute("cart");
        request.getSession().removeAttribute("item");
        response.sendRedirect("UserProfile");
    }

}
