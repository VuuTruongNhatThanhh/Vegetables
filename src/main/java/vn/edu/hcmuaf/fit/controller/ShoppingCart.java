package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.PreferentialDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Cart;
import vn.edu.hcmuaf.fit.model.CartDetails;
import vn.edu.hcmuaf.fit.model.Preferential;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Date;
import java.util.Collection;

@WebServlet(name = "ShoppingCart", value = "/ShoppingCart")
public class ShoppingCart extends HttpServlet {
    String error = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        Collection<CartDetails> items = cart.getListCartDetails();
        request.setAttribute("size", items.size());
        request.setAttribute("error", error);
        request.getSession().setAttribute("items", items);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        String sale = request.getParameter("sale");
        Preferential p = PreferentialDao.getInstance().get(sale);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (p == null) {
            error = "Mã sử dụng không đúng";
            User uu = (User) request.getSession().getAttribute("auth");
            DB.me().insert(new Log(Log.INFO,uu.getId(),ipAddress,"DISCOUNT CODE","Khách hàng nhập mã ưu đãi không đúng",0));
        } else {
            if (cart != null) {
                Date now = new Date(System.currentTimeMillis());
                if (now.compareTo(p.getDayStart()) >= 0 && now.compareTo(p.getDayEnd()) <= 0) {
                    cart.setSale(p.getMoney());
                    error = "";
                    User uu = (User) request.getSession().getAttribute("auth");
                    DB.me().insert(new Log(Log.INFO,uu.getId(),ipAddress,"DISCOUNT CODE","Khách hàng nhập mã ưu đãi thành công",0));
                } else {
                    error = "Mã ưu đãi đã hết hạn";
                    User uu = (User) request.getSession().getAttribute("auth");
                    DB.me().insert(new Log(Log.INFO,uu.getId(),ipAddress,"DISCOUNT CODE","Khách hàng nhập mã ưu đãi bị hết hạn",0));
                }
            } else {
                error = "Bạn phải mua hàng mới được sử dụng";
                User uu = (User) request.getSession().getAttribute("auth");
                DB.me().insert(new Log(Log.INFO,uu.getId(),ipAddress,"DISCOUNT CODE","Khách hàng chưa mua hàng nên không nhập được mã ưu đãi",0));
            }
        }
        response.sendRedirect("/ShoppingCart");
    }
}
