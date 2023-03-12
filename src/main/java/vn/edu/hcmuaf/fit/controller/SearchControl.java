package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "SearchControl", urlPatterns = {"/search"})
public class SearchControl extends HttpServlet {
    List<Product> lists;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("txt");
        int count = ProductDao.getInstance().getTotalBySearch(request.getParameter("txt"));
        int endpage = count / 24;
        if (count % 24 != 0) {
            endpage++;
        }
        String spageid = request.getParameter("page");
        if (spageid == null) {
            spageid = "1";
        }
        int pageid = Integer.parseInt(spageid);
        request.setAttribute("tag", pageid);
        if (pageid != 1) {
            pageid = pageid - 1;
            pageid = pageid * 24 + 1;
        }
        String sort = request.getParameter("sort");
        if (sort != null) {
            sortBy(sort);
            sort = "&sort=" + sort;
        } else {
            sort = "";
        }
        lists = ProductDao.getInstance().searchByName(request.getParameter("txt"), pageid, 24);
        request.setAttribute("listP", lists);
        request.setAttribute("endp", endpage);
        request.setAttribute("sort", sort);
        request.setAttribute("title", "Kết quả tìm kiếm của '" + request.getParameter("txt") + "'");
        request.getRequestDispatcher("product1.jsp").forward(request, response);
        User uu = (User) request.getSession().getAttribute("auth");
        DB.me().insert(new Log(Log.INFO,uu.getId(),"SEARCH","Tìm kiếm: "+keyword.toString(),0));
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
