package poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Confirmable {
    private int number;
    private LocalDateTime date;
    private Status state;
    private double total;
    private List<ProductOrder> productOrders;  // List to hold ProductOrder objects

    // Constructor
    public Order(int number) {
        this.number = number;
        this.date = LocalDateTime.now();
        this.state = Status.Pending;
        this.total = 0.0;
        this.productOrders = new ArrayList<>();  // Initialize the list
    }

    // Method to add a ProductOrder to the order
    public void addProduct(ProductOrder productOrder) {
        productOrders.add(productOrder);
        // Update total when a new product is added
        total += productOrder.getProduct().getPrice() * productOrder.getQty();
    }

    @Override
    public void confirm() {
        if (state == Status.Pending) {
            state = Status.Confirmed;
        } else {
            throw new InvalidOperationException("Order cannot be confirmed from its current state: " + state);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ").append(number).append(" | ").append(state).append(" | ");

        // Calculate total before and after discounts
        double totalBeforeDiscount = 0.0;
        double totalAfterDiscount = 0.0;
        StringBuilder promoDetails = new StringBuilder();

        for (ProductOrder productOrder : productOrders) {
            double priceBeforeDiscount = productOrder.getProduct().getPrice() * productOrder.getQty();
            double priceAfterDiscount = priceBeforeDiscount - productOrder.getProduct().getPromo() * productOrder.getQty();

            totalBeforeDiscount += priceBeforeDiscount;
            totalAfterDiscount += priceAfterDiscount;

            if (productOrder.getProduct().getPromo() > 0) {
                promoDetails.append(productOrder.getProduct().getTitle())
                             .append(": -")
                             .append(String.format("%.2f", productOrder.getProduct().getPromo() * productOrder.getQty()))
                             .append(" €, ");
            }
        }

        sb.append(String.format("%.2f", totalBeforeDiscount)).append(" €");

        if (promoDetails.length() > 0) {
            sb.append(" (").append(promoDetails.substring(0, promoDetails.length() - 2)).append(")");
            sb.append(" => ").append(String.format("%.2f", totalAfterDiscount)).append(" €");
        }

        return sb.toString();
    }

    // Getters and Setters for other fields (if needed)
    public int getNumber() {
        return number;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Status getState() {
        return state;
    }

    public double getTotal() {
        return total;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }
}

