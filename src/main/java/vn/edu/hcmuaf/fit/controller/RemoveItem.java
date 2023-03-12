package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Cart;
import vn.edu.hcmuaf.fit.model.CartDetails;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

@WebServlet(name = "RemoveItem", value = "/RemoveItem")
public class RemoveItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        String idP = request.getParameter("idP");
        String idW = request.getParameter("idW");
        if (idP == null && idW == null) {
            cart.removeAll();
        } else {
            String id = idP + "-" + idW;
            cart.remove(id);
        }
        Collection<CartDetails> items = cart.getListCartDetails();
        request.setAttribute("size", items.size());
        request.getSession().setAttribute("items", items);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
        User uu = (User) request.getSession().getAttribute("auth");
        DB.me().insert(new Log(Log.INFO,uu.getId(),ipAddress,"CART","Xoá sản phẩm: Mã sp: "+idP+", Mã khối lượng: "+ idW+" ( null nghĩa là khách hàng đã xóa hết giỏ hàng)",0));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
