package vn.edu.hcmuaf.fit.model;


import vn.edu.hcmuaf.fit.Dao.ShipmentDetailDao;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Bills {
    private String id;
    private Date date;
    private double total;
    private int state;
    private String idUser;

    public Bills(String id, Date date, double total, int state, String idUser) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.state = state;
        this.idUser = idUser;
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

}
