package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.List;

@WebServlet(name = "RemoveProductAdmin", value = "/RemoveProductAdmin")
public class RemoveProductAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress addr = InetAddress.getLocalHost();

        //Host IP Address
        String ipAddress = addr.getHostAddress();
        //Hostname
        String hostname = addr.getHostName();
        User uu = (User) request.getSession().getAttribute("auth");
        String idP = request.getParameter("idP");
        DB.me().insert(new Log(Log.DANGER,uu.getId(),ipAddress,"MANAGE PRODUCT","Xóa sản phẩm: "+ProductDao.getInstance().selectName(idP),0));

        ProductDao.getInstance().delete(idP);
        Product p = ProductDao.getInstance().getProductById(idP);
        PrintWriter out = response.getWriter();
        if (p != null) {
            out.println(
                    "                                <th scope=\"row\">" + p.getId() + "</th>\n" +
                            "                                <td>\n" +
                            "                                    <a>Hiện có " + p.getPics().size() + " ảnh</a>\n" +
                            "                                </td>\n" +
                            "                                <td>" + p.getName() + "</td>\n" +
                            "                                <td><a>Hiện có " + p.getWeights().size() + " khối lượng</a></td>\n" +
                            "                                <td>" + p.getDiscount() + "<span>%</span></td>\n" +
                            "                                   <td>" + p.getDescribe() + "</td>\n" +
                            "                                <td>" + p.getDate() + "</td>\n" +
                            getStringCondition(p.isDelete()) +
                            "                                <td>" + p.getNameType() + "</td>\n" +
                            "                                <td>\n" +
                            "                                    <button onclick=\"removeP('" + p.getId() + "')\" class=\"btn btn-primary btn-sm trash\"\n" +
                            "                                            type=\"button\" title=\"Xóa\">\n" +
                            "                                        <i class=\"fas fa-trash-alt\"></i>\n" +
                            "                                    </button>\n" +
                            "                                    <button class=\"btn btn-primary btn-sm edit\" type=\"button\" title=\"Sửa\"\n" +
                            "                                            data-toggle=\"modal\" data-target=\"#ModalUP\">\n" +
                            "                           <a style=\"color: white;\"  href=\"UpdateProductAdmin?id=" + p.getId() + "&idType=" + p.getIdType() + "\"><i class=\"fas fa-edit\"></i></a>\n" +
                            "                                    </button>\n" +
                            "                                </td>\n");
        } else {
            out.println("");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String getStringCondition(boolean check) {
        if (check)
            return "<td><span class=\"badge bg-success\">Còn bán</span></td>\n";
        return "<td><span class=\"badge bg-danger\">Ngừng bán</span></td>\n";

    }
}
