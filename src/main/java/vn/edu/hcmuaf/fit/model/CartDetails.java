package vn.edu.hcmuaf.fit.model;

public class CartDetails {

    private String id;
    private Product product;
    private Weight weight;
    private int quanity;
    private double price;

    public CartDetails() {
        this.product = new Product();
        this.weight = new Weight();
        this.quanity = 0;
        this.price = 0;
    }

    public CartDetails(Product product, Weight weight) {
        this.product = product;
        this.weight = weight;
    }

    public CartDetails(Product product, Weight weight, int quanity) {
        this.id = product.getId() + "-" + weight.getId();
        this.product = product;
        this.weight = weight;
        this.quanity = quanity;
        this.price = getPricePW() * quanity;
    }

    public CartDetails(Product product, Weight weight, int quanity, double price) {
        this.id = product.getId() + "-" + weight.getId();
        this.product = product;
        this.weight = weight;
        this.quanity = quanity;
        this.price = price;
    }

    public double getPricePW() {
        if (this.product.getDiscount() != 0) {
            return this.product.priceDiscount(this.weight.getId());
        }
        return this.weight.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlPic() {
        return this.product.getPicture(0);
    }

    public int setAmount() {
        return (this.weight.getCount() - this.quanity) <= 0 ? 0 : (this.weight.getCount() - this.quanity);
    }

    @Override
    public String toString() {
        return this.product.getName() + "-" + this.quanity;
    }
}
