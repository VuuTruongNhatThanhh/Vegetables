package vn.edu.hcmuaf.fit.model;


import vn.edu.hcmuaf.fit.Dao.*;
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
    private double fee;
    private String hash;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Bills(String id, Date date, double total, int state, String idUser, String idinfo, double fee, String hash) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.state = state;
        this.idUser = idUser;
        this.idinfo = idinfo;
        this.fee = fee;
        this.hash = hash;
    }

    public Bills(String id, Date date, double total, int state, String idUser, String idinfo, double fee) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.state = state;
        this.idUser = idUser;
        this.idinfo = idinfo;
        this.fee = fee;
    }

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

    public Bills() {
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
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
    String getName = KeyDao.getInstance().getNameUser(idinfo);
    String getPhone = KeyDao.getInstance().getPhoneUser(idinfo);
    String getProvince = KeyDao.getInstance().getProvinceUser(idinfo);
    String getDistrict = KeyDao.getInstance().getDistrictUser(idinfo);
    String getWard = KeyDao.getInstance().getWardUser(idinfo);
    String getAddress = KeyDao.getInstance().getAddressUser(idinfo);
    String publickey = KeyDao.getInstance().getpublickey(idUser);




    public boolean verify(String idbill, String idinfo, String iduser, String hash_sign) throws Exception {

       return KeyDao.verifySignature(KeyDao.getInstance().getidProductInBill(idbill) + KeyDao.getInstance().getWeightProductInBill(idbill) + KeyDao.getInstance().getAmountProductInBill(idbill) + KeyDao.getInstance().getPriceProductInBill(idbill) + KeyDao.getInstance().getDateBill(idbill)
               + KeyDao.getInstance().getTotalBill(idbill)  + KeyDao.getInstance().getIDuserBill(idbill) + KeyDao.getInstance().getIDinfoBill(idbill) + KeyDao.getInstance().getFeeBill(idbill) + KeyDao.getInstance().getNameUser(idinfo) + KeyDao.getInstance().getPhoneUser(idinfo)
               + KeyDao.getInstance().getProvinceUser(idinfo) + KeyDao.getInstance().getDistrictUser(idinfo) + KeyDao.getInstance().getWardUser(idinfo) + KeyDao.getInstance().getAddressUser(idinfo), hash_sign,KeyDao.getInstance().getpublickey(iduser));


    }

    public void updateBillchanged(String id){
         KeyDao.getInstance().updateBillChanged(id);
    }






}
