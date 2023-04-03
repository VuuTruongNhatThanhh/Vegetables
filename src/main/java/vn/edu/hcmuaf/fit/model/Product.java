package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class Product {
    private String id;
    private String name;
    private double discount;
    private String describe;
    private String idType;
    private Date date;
    private boolean isDelete;
    private List<Pictures> pics;
    private List<Weight> weights;

    public Product() {
        this.pics = new LinkedList<>();
        this.weights = new LinkedList<>();
    }

    public Product(String id, String name, double discount, String describe, Date date, boolean isDelete, String idType) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.describe = describe;
        this.idType = idType;
        this.date = date;
        this.isDelete = isDelete;
    }

    public String getDate() {
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return f.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<Pictures> getPics() {
        return pics;
    }

    public void setPics(List<Pictures> pics) {
        this.pics = pics;
    }

    public List<Weight> getWeights() {
        return weights;
    }

    public void setWeights(List<Weight> weights) {
        this.weights = weights;
    }

    public int getPriceWeight(int i) {
        return this.weights.get(i).getPrice();
    }

    public String getPicture(int i) {
        return "/" + this.pics.get(i).getUrl();
    }

    public double priceDiscount(int i) {
        return getPriceWeight(i) - (getPriceWeight(i) * (discount / 100));
    }

    public double priceDiscount(String id) {
        for (Weight w : this.weights)
            if (id.equalsIgnoreCase(w.getId())) return w.getPrice() - (w.getPrice() * (this.discount / 100));
        return 0;
    }

    public int getDis() {
        return (int) this.discount;
    }

    public String getNameType() {
        return TypeProductDao.getInstance().getType(this.idType);
    }

    public String getIdWeight(int i) {
        return this.weights.get(i).getId();
    }

    public int getAmountWeight(int i) {
        return this.weights.get(i).getCount();
    }

    public boolean haveWeightOver() {
        if (this.weights.isEmpty()) return true;
        for (Weight w : this.weights)
            if (w.checkAmount())
                return true;
        return false;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id;
    }

}
