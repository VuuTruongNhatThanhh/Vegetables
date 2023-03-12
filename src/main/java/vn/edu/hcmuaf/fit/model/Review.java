package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.Dao.UserDao;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Review {
    private String idP;
    private String idU;
    private Date date;
    private String content;
    private int stars;

    public Review(String idP, String idU, Date date, String content, int stars) {
        this.idP = idP;
        this.idU = idU;
        this.date = date;
        this.content = content;
        this.stars = stars;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public String getIdU() {
        return idU;
    }

    public void setIdU(String idU) {
        this.idU = idU;
    }

    public String getDate() {
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return f.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getName() {
        return UserDao.getInstance().getNameById(idU);
    }

    @Override
    public String toString() {
        return "Review{" +
                "idP='" + idP + '\'' +
                ", idU='" + idU + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", stars=" + stars +
                '}';
    }
}
