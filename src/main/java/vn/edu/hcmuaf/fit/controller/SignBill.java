package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.Dao.KeyDao;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignBill", value = "/SignBill")
public class SignBill extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String privateKey = request.getParameter("key");
        boolean isValid = KeyDao.isValidDSAPrivateKey(privateKey);
        User user = (User) request.getSession().getAttribute("auth");
        String newIDbill = KeyDao.getInstance().getnewIDbill(user.getId());
        String getidProductInBill = KeyDao.getInstance().getidProductInBill(newIDbill);
        String getWeight = KeyDao.getInstance().getWeightProductInBill(newIDbill);
        String getAmount = KeyDao.getInstance().getAmountProductInBill(newIDbill);
        String getPrice = KeyDao.getInstance().getPriceProductInBill(newIDbill);
        String getDate = KeyDao.getInstance().getDateBill(newIDbill);
        String getTotal = KeyDao.getInstance().getTotalBill(newIDbill);
        String getStatus = KeyDao.getInstance().getStatusBill(newIDbill);
        String getIDUser = KeyDao.getInstance().getIDuserBill(newIDbill);
        String getIDinfo = KeyDao.getInstance().getIDinfoBill(newIDbill);
        String getfee = KeyDao.getInstance().getFeeBill(newIDbill);
        String getName = KeyDao.getInstance().getNameUser(getIDinfo);
        String getPhone = KeyDao.getInstance().getPhoneUser(getIDinfo);
        String getProvince = KeyDao.getInstance().getProvinceUser(getIDinfo);
        String getDistrict = KeyDao.getInstance().getDistrictUser(getIDinfo);
        String getWard = KeyDao.getInstance().getWardUser(getIDinfo);
        String getAddress = KeyDao.getInstance().getAddressUser(getIDinfo);
        String publickey = KeyDao.getInstance().getpublickey(user.getId());










        if(isValid){
            try {

                    String signature = KeyDao.signData(getidProductInBill + getWeight + getAmount + getPrice + getDate
                            + getTotal + getIDUser + getIDinfo + getfee + getName + getPhone
                            + getProvince + getDistrict + getWard + getAddress, privateKey);
                    if (KeyDao.verifySignature(getidProductInBill + getWeight + getAmount + getPrice + getDate
                            + getTotal + getIDUser + getIDinfo + getfee + getName + getPhone
                            + getProvince + getDistrict + getWard + getAddress, signature, publickey)) {
                        KeyDao.getInstance().updateHashbill(signature, newIDbill);


                        response.sendRedirect("OderStatus");
                    } else {
                        String noti1 = "Private key không hợp lệ";
                        request.setAttribute("noti1", noti1);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("signBill.jsp");
                        dispatcher.forward(request, response);
                    }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            String noti1 = "Private key không hợp lệ";
            request.setAttribute("noti1", noti1);
            RequestDispatcher dispatcher = request.getRequestDispatcher("signBill.jsp");
            dispatcher.forward(request, response);
        }
    }
}
