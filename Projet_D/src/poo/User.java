package poo;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Order> orders;

    public User(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}