package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "ListProduct", value = "/ListProduct")
public class ListProduct extends HttpServlet {
    List<Product> lists;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idType = request.getParameter("type");
        int count = ProductDao.getInstance().getTotalProduct(idType);
        int endpage = count / 24;
        if (count % 24 != 0) {
            endpage++;
        }
        String sPageId = request.getParameter("page");
        if (sPageId == null) {
            sPageId = "1";
        }
        int pageid = Integer.parseInt(sPageId);
        request.setAttribute("tag", pageid);
        if (pageid != 1) {
            pageid = pageid - 1;
            pageid = pageid * 24 + 1;
        }
        String title = "Sản Phẩm";
        lists = ProductDao.getInstance().getProductPage(pageid, 24, idType);
        if (idType != null) {
            lists.addAll(ProductDao.instance.getByParentType(idType, pageid, 24));
            title = TypeProductDao.getInstance().getType(idType);
        }
        String sort = request.getParameter("sort");
        if (sort != null) {
            sortBy(sort);
            sort = "&sort=" + sort;
        } else {
            sort = "";
        }
        List<Product> discounts = ProductDao.getInstance().getDiscount();
        request.setAttribute("discounts", discounts);
        request.setAttribute("listP", lists);
        request.setAttribute("endp", endpage);
        request.setAttribute("type", idType);
        request.setAttribute("sort", sort);
        request.setAttribute("title", title);
        request.getRequestDispatcher("product1.jsp").forward(request, response);
//        DB.me().insert(new Log(Log.INFO, "TK1","LIST PRODUCT", lists.toString(),0));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void sortBy(String sort) {
        Comparator<Product> cmp = null;
        switch (sort) {
            case "a_z":
                cmp = (p1, p2) -> {
                    return p1.getName().compareTo(p2.getName());
                };
                break;
            case "z_a":
                cmp = (p1, p2) -> {
                    return p2.getName().compareTo(p1.getName());
                };
                break;
            case "price":
                cmp = (p1, p2) -> {
                    return p1.getPriceWeight(0) - p2.getPriceWeight(0);
                };
                break;
            case "price2":
                cmp = (p1, p2) -> {
                    return p2.getPriceWeight(0) - p1.getPriceWeight(0);
                };
                break;
            default:
                return;
        }

        Collections.sort(lists, cmp);
    }
}
