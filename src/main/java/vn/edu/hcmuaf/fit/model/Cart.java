package vn.edu.hcmuaf.fit.model;

import java.util.*;

public class Cart {
    private HashMap<String, CartDetails> data;
    private User customer;
    private int quanity;
    private double sale;
    private double total;

    public Cart() {
        this.data = new HashMap<>();
        this.customer = new User();
        this.sale = 0;
        this.quanity = 0;
        this.total = 0;
    }

    public Cart(User customer, int quanity, double total) {
        this.data = new HashMap<>();
        this.customer = customer;
        this.quanity = quanity;
        this.total = total;
    }

    public Collection<CartDetails> getListCartDetails() {
        return this.data.values();
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
        UpdateTotalMoneyAndQuanity();
    }

    public void put(CartDetails cd, int num) {
        if (this.data.containsKey(cd.getId())) {
            cd = this.data.get(cd.getId());
            cd.setQuanity(cd.getQuanity() + num);
            cd.setPrice(cd.getPricePW() * cd.getQuanity());
        }
        this.data.put(cd.getId(), cd);
        UpdateTotalMoneyAndQuanity();
    }

    public void remove(String key) {
        this.data.remove(key);
        UpdateTotalMoneyAndQuanity();
    }

    public void update(String id, int num) {
        if (this.data.containsKey(id)) {
            CartDetails cd = this.data.get(id);
            cd.setQuanity(cd.getQuanity() + num);
            cd.setPrice(cd.getPricePW() * cd.getQuanity());
            if (cd.getQuanity() == 0) {
                remove(id);
            } else {
                this.data.put(cd.getId(), cd);
            }
        }
        UpdateTotalMoneyAndQuanity();
    }

    public void removeAll() {
        this.data.clear();
        UpdateTotalMoneyAndQuanity();
    }

    public double totalMoneyItem() {
        double res = 0;
        for (CartDetails cd : this.data.values())
            res += cd.getPrice();
        return res;
    }

    private void UpdateTotalMoneyAndQuanity() {
        this.total = 0;
        this.quanity = 0;
        for (CartDetails cd : this.data.values()) {
            this.quanity += cd.getQuanity();
            this.total += cd.getPrice();
        }
        this.total -= this.sale;
    }
}
