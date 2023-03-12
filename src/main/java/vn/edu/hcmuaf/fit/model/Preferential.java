package vn.edu.hcmuaf.fit.model;


import java.sql.Date;

public class Preferential {
    private String id;
    private double money;
    private Date dayStart;
    private Date dayEnd;

    public Preferential(String id, double money, Date dayStart, Date dayEnd) {
        this.id = id;
        this.money = money;
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getDayStart() {
        return dayStart;
    }

    public void setDayStart(Date dayStart) {
        this.dayStart = dayStart;
    }

    public Date getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(Date dayEnd) {
        this.dayEnd = dayEnd;
    }
}
