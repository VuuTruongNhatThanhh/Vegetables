package vn.edu.hcmuaf.fit.model;


import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.Dao.BillDetailDao;
import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.ShipmentDetailDao;
import vn.edu.hcmuaf.fit.controller.ListProduct;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Bills {
    private String id;
    private Date date;
    private double total;
    private int state;
    private String idUser;

    private String idinfo;

    public Bills(String id, Date date, double total, int state, String idUser, String idinfo) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.state = state;
        this.idUser = idUser;
        this.idinfo = idinfo;
    }

    public Bills(String id, Date date, double total, int state, String idUser) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.state = state;
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdinfo() {
        return idinfo;
    }

    public void setIdinfo(String idinfo) {
        this.idinfo = idinfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return f.format(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ShipmentDetails getShip() {
        return ShipmentDetailDao.getInstance().get(this.idUser);
    }
    public String getNameReceive(){
        return ShipmentDetailDao.getInstance().NameUser(idinfo);
    }
    public String getPhoneReceive(){
        return ShipmentDetailDao.getInstance().PhoneUser(idinfo);
    }
    public String getAdressReceive(){
        return ShipmentDetailDao.getInstance().AddressUser(idinfo);
    }
    public String getProvinceReceive(){
        return ShipmentDetailDao.getInstance().ProvinceUser(idinfo);
    }
    public String getDistrictReceive(){
        return ShipmentDetailDao.getInstance().DistrictUser(idinfo);
    }
    public String getWardReceive(){
        return ShipmentDetailDao.getInstance().WardUser(idinfo);
    }
    public List<Product> getProductName(){
        return ProductDao.getInstance().selectProductNameInBill(id);
    }
    public String gettotalprice(){
        return BillDao.getInstance().totalPrice(id);
    }






}
