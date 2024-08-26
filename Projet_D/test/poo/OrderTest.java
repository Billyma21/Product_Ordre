package poo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class OrderTest {

    @Test
    void testOrderCreation() {
        Order order = new Order(1);
        assertTrue(order instanceof Confirmable);
        assertEquals(1, order.getNumber());
        assertNotNull(order.getDate());
        assertEquals(Status.Pending, order.getState());
        assertEquals(0.0, order.getTotal());
        assertTrue(order.getProductOrders().isEmpty());
    }
    @Test
    void testToString() {
        Order order = new Order(2);
        Product product1 = new Product(101, "Lait", 50.00, 0.50);
        Product product2 = new Product(102, "Miel", 50.00, 9.00);
        order.addProduct(new ProductOrder(order, product1, 1));
        order.addProduct(new ProductOrder(order, product2, 1));

        // Vérif que le format de la chaîne correspond exactement à ce qui est attendu
        assertEquals("Order 2 | Pending | 100,00 € (Lait: -0,50 €, Miel: -9,00 €) => 90,50 €", order.toString());

        order.confirm();
        assertEquals("Order 2 | Confirmed | 100,00 € (Lait: -0,50 €, Miel: -9,00 €) => 90,50 €", order.toString());
    }

    @Test
    void testConfirm() {
        Order order = new Order(3);

        assertEquals(Status.Pending, order.getState());
        order.confirm();
        assertEquals(Status.Confirmed, order.getState());

        InvalidOperationException thrown = assertThrows(InvalidOperationException.class, () -> {
            order.confirm();
        });
        assertEquals("Order cannot be confirmed from its current state: Confirmed", thrown.getMessage());
    }

}

