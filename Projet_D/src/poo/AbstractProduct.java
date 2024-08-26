package poo;

public abstract class AbstractProduct {
    protected String title;
    protected double price;

    public AbstractProduct(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}