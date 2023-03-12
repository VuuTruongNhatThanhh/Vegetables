package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.WeightDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

@WebServlet(name = "AddCart", value = "/AddCart")
public class AddCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idP = request.getParameter("idP");
        String idW = request.getParameter("idW");
        String num = request.getParameter("num");
        String url = request.getParameter("url");
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        url = url.replaceAll("-", "&");
        Product p = ProductDao.getInstance().getProductById(idP);
        Weight w = WeightDao.getInstance().getWeightById(idW);
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }
        if (num == null) {
            num = "1";
        }
        int quanity = Integer.parseInt(num);
        CartDetails cd = new CartDetails(p, w, quanity);
        Collection<CartDetails> items = cart.getListCartDetails();
        cart.put(cd, quanity);
        request.getSession().setAttribute("cart", cart);
        request.getSession().setAttribute("item", items);
        response.sendRedirect(url);
        User uu = (User) request.getSession().getAttribute("auth");
        DB.me().insert(new Log(Log.INFO,uu.getId(),ipAddress,"CART","Thêm vào giỏ hàng: Mã sp: "+idP+", Mã khối lượng: "+ idW+", Số lượng: "+num,0));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
