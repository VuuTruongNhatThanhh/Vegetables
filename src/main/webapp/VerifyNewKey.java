package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.KeyDao;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VerifyNewKey", value = "/VerifyNewKey")
public class VerifyOrderStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String privateKey = request.getParameter("key");
        String id = request.getParameter("id");
        boolean isValid = KeyDao.isValidDSAPrivateKey(privateKey);
        User user = (User) request.getSession().getAttribute("auth");
        String getidProductInBill = KeyDao.getInstance().getidProductInBill(id);
        String getWeight = KeyDao.getInstance().getWeightProductInBill(id);
        String getAmount = KeyDao.getInstance().getAmountProductInBill(id);
        String getPrice = KeyDao.getInstance().getPriceProductInBill(id);
        String getDate = KeyDao.getInstance().getDateBill(id);
        String getTotal = KeyDao.getInstance().getTotalBill(id);
        String getStatus = KeyDao.getInstance().getStatusBill(id);
        String getIDUser = KeyDao.getInstance().getIDuserBill(id);
        String getIDinfo = KeyDao.getInstance().getIDinfoBill(id);
        String getfee = KeyDao.getInstance().getFeeBill(id);
        String getName = KeyDao.getInstance().getNameUser(getIDinfo);
        String getPhone = KeyDao.getInstance().getPhoneUser(getIDinfo);
        String getProvince = KeyDao.getInstance().getProvinceUser(getIDinfo);
        String getDistrict = KeyDao.getInstance().getDistrictUser(getIDinfo);
        String getWard = KeyDao.getInstance().getWardUser(getIDinfo);
        String getAddress = KeyDao.getInstance().getAddressUser(getIDinfo);
        String publickey = KeyDao.getInstance().getpublickey(user.getId());
        int id_publickey = KeyDao.getInstance().getIDpublickey(user.getId());


        if(isValid){
            try {

                String signature = KeyDao.signData(getidProductInBill + getWeight + getAmount + getPrice + getDate
                        + getTotal + getIDUser + getIDinfo + getfee + getName + getPhone
                        + getProvince + getDistrict + getWard + getAddress, privateKey);
                if (KeyDao.verifySignature(getidProductInBill + getWeight + getAmount + getPrice + getDate
                        + getTotal + getIDUser + getIDinfo + getfee + getName + getPhone
                        + getProvince + getDistrict + getWard + getAddress, signature, publickey)) {
                    KeyDao.getInstance().updateHashbill(signature,id_publickey, id);


                    response.sendRedirect("OderStatus");
                } else {
                    String noti1 = "Private key không hợp lệ";
                    request.setAttribute("noti1", noti1);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("signBillAgain.jsp");
                    dispatcher.forward(request, response);
                }

            } catch (Exception e) {
               
            }
        }else{
            String noti1 = "Private key không hợp lệ";
            request.setAttribute("noti1", noti1);
            RequestDispatcher dispatcher = request.getRequestDispatcher("signBillAgain.jsp");
            dispatcher.forward(request, response);
        }
    }

}
