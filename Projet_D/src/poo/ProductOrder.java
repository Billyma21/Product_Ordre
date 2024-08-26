package poo;

public class ProductOrder {
    private Order order;
    private Product product;
    private int qty;

    // Constructor
    public ProductOrder(Order order, Product product, int qty) {
        this.order = order;
        this.product = product;
        this.qty = qty;
    }

    // Getters
    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQty() {
        return qty;
    }
}

