package poo;

public class Product extends AbstractProduct {
    private int id;
    private double promo;

    public Product(int id, String title, double price, double promo) {
        super(title, price);
        this.id = id;
        this.promo = promo;
    }

    public int getId() {
        return id;
    }

    public double getPromo() {
        return promo;
    }
}