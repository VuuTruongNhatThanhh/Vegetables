package vn.edu.hcmuaf.fit.controller.API;

//import beans.Cart;
import com.google.gson.Gson;
import org.json.JSONException;
import vn.edu.hcmuaf.fit.services.API_LOGISTIC.District;
import vn.edu.hcmuaf.fit.services.API_LOGISTIC.District_API;
import vn.edu.hcmuaf.fit.services.API_LOGISTIC.Login_API;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DistrictServlet", urlPatterns = "/DistrictServlet")
public class AddressServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddressServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String provinceId = request.getParameter("province");
        int value = Integer.parseInt(provinceId);
        Login_API login_api = new Login_API();
        System.out.println(value);
        String API_KEY = null;
        try {
            API_KEY = login_api.login();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        List<District> districts = District_API.convert(API_KEY, value);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String json = gson.toJson(districts);
        out.print(json);
        out.flush();
    }


}
