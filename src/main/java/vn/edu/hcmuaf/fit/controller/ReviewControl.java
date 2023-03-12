package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ReviewDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Review;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ReviewControl", value = "/ReviewControl")
public class ReviewControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idP");
        String load = request.getParameter("amount");
        int amount = Integer.parseInt(load);
        List<Review> list = ReviewDao.getInstance().getReview(id, amount);
        int amountReview = ReviewDao.getInstance().amountReview(id);
        PrintWriter out = response.getWriter();
        for (Review r : list) {
            out.println("      <li class=\"comment\">\n" +
                    "            <div class=\"vcard bio\">\n" +
                    "                <i class=\"fa-solid fa-user\"></i>\n" +
                    "            </div>\n" +
                    "            <div class=\"comment-body\">\n" +
                    "                <h3>" + r.getName() + "</h3>\n" +
                    "                <div class=\"rating d-flex\">\n" +
                    "                    <div class=\"stars text-left mr-4\">\n" +
                    "                        <form style=\"margin: 0\" action=\"\">\n" +
                    printStar(r.getStars()) +
                    "                        </form>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <div class=\"meta\"><i>" + r.getDate() + "</i></div>\n" +
                    "                <p>" + r.getContent() + "</p>\n" +
                    "            </div>\n" +
                    "        </li>");
        }
        if (list.size() < amountReview) {
            out.println(" <button id=\"load\" style=\"padding-left: 100%; padding-right: 100%;\" class=\"btn btn-light\"\n" +
                    "                onclick=\"loadMore('${idP}')\"><i\n" +
                    "                class=\"arrow down\"></i></button>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idP = request.getParameter("idP");
        User u = (User) request.getSession().getAttribute("auth");
        String mess = request.getParameter("mess");
        String star = request.getParameter("star");
        String url = request.getParameter("url");
        ReviewDao.getInstance().addReview(idP, u.getId(), mess, Integer.parseInt(star));
        url = url.replaceAll("-", "&");
        response.sendRedirect(url);
        User uu = (User) request.getSession().getAttribute("auth");
        DB.me().insert(new Log(Log.INFO,uu.getId(),"COMMENT","Bình luận: "+mess.toString()+", Số sao: "+star.toString(),0));
    }

    private String printStar(int amount) {
        String res = "  <input class=\"star star-" + amount + " check\" type=\"radio\" name=\"star\" />\n" +
                "                            <label class=\"star star" + amount + "\" for=\"star-" + amount + "\"></label>\n";
        amount--;
        for (int i = amount; i > 0; i--) {
            res += "  <input class=\"star star-" + i + "\" type=\"radio\" name=\"star\"/>\n" +
                    "                            <label class=\"star star" + i + "\" for=\"star-" + i + "\"></label>\n";
        }
        return res;
    }
}
