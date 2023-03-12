package vn.edu.hcmuaf.fit.model;

public class Weight {
    private String id;
    private int weight;
    private int price;
    private int amount;

    public Weight() {

    }

    public Weight(String id, int weight, int price, int amount) {
        this.id = id;
        this.weight = weight;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return amount;
    }

    public void setCount(int count) {
        this.amount = count;
    }

    public boolean checkAmount(){
        return this.amount == 0;
    }
}
