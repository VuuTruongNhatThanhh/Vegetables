package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.Dao.ShipmentDetailDao;

public class User {
    private String id;
    private String email;
    private String password;
    private String name;
    private int roleId;
    private String myHash;
    private int activate;
    private String date_sendmail_verify;




    public User(String id, String email, String password, String name, int roleId, String myHash, int activate, String date_sendmail_verify) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.roleId = roleId;
        this.myHash = myHash;
        this.activate = activate;
        this.date_sendmail_verify = date_sendmail_verify;
    }

    public User(String id, String email, String password, String name, int roleId, String myHash) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.roleId = roleId;
        this.myHash = myHash;

    }

    public User() {

    }

    public User(String name, String newpass, int roleId) {
    }





    public String getMyHash() {
        return myHash;
    }

    public void setMyHash(String myHash) {
        this.myHash = myHash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public ShipmentDetails getShip() {
        return ShipmentDetailDao.getInstance().get(this.id);
    }

    public String getNameRole() {
        String res = "";
        switch (this.roleId) {
            case 0:
                res = "Admin";
                break;
            case 1:
                res = "Mod";
                break;
            case 2:
                res = "User";
                break;

        }
        return res;
    }
    public int getActivate() {
        return activate;
    }


    public void setActivate(int activate) {
        this.activate = activate;
    }

    public String getDate_sendmail_verify() {
        return date_sendmail_verify;
    }

    public void setDate_sendmail_verify(String date_sendmail_verify) {
        this.date_sendmail_verify = date_sendmail_verify;
    }
    public String getNameActivate() {
        String res = "";
        switch (this.activate) {
            case 0:
                res = "Chưa kích hoạt";
                break;
            case 1:
                res = "Đã kích hoạt";
                break;

        }
        return res;
    }
}
