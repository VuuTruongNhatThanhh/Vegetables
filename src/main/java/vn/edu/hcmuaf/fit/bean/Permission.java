package vn.edu.hcmuaf.fit.bean;

import vn.edu.hcmuaf.fit.Dao.BillDetailDao;
import vn.edu.hcmuaf.fit.Dao.permissionDao;

import java.io.Serializable;
import  java.util.List;

public class Permission implements Serializable {
    private int id;
    private String resource;
    private String u_id;
    private  int per;

    public Permission() {
    }

    public Permission(int id, String resource, String u_id, int per) {
        this.id = id;
        this.resource = resource;
        this.u_id = u_id;
        this.per = per;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public int getPer() {
        return per;
    }

    public void setPer(int per) {
        this.per = per;
    }

    public String getNamePer() {
        String res = "";
        switch (this.per) {
            case 0:
                res = "Toàn quyền";
                break;
            case 1:
                res = "Xem";
                break;
            case 2:
                res = "Không có quyền";
                break;



        }
        return res;
    }

    public String getNameRs() {
        String res = "";
        switch (this.resource) {
            case "0":
                res = "Trang chủ";
                break;
            case "1":
                res = "Quản lý sản phẩm";
                break;
            case "2":
                res = "Quản lý log";
                break;
            case "6":
                res = "Quản lý loại sản phẩm";
                break;
            case "7":
                res = "Quản lý hóa đơn";
                break;
            case "8":
                res = "Quản lý tài khoản";
                break;
            case "9":
                res = "Quản lý thăng/ hạ quyền tài khoản";
                break;
            case "10":
                res = "Quản lý bình luận";
                break;

            case "12":
                res = "Quản lý ưu đãi";
                break;



        }
        return res;

    }

    public String getNameRole(){
        String rs= permissionDao.getInstance().selectrolename(u_id);
        switch (rs) {
            case "0":
                rs = "Admin";
                break;
            case "1":
                rs = "Mod";
                break;
            case "2":
                rs = "User";
                break;



        }
        return rs;

    }



}
