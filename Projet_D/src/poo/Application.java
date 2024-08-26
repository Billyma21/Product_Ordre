package poo;

public class Application {
    public static void main(String[] args) {
        try {
            // Create a sample Order
            Order order = new Order(1);
            Product product1 = new Product(101, "Lait", 50.00, 0.50);
            Product product2 = new Product(102, "Miel", 50.00, 9.00);
            order.addProduct(new ProductOrder(order, product1, 1));
            order.addProduct(new ProductOrder(order, product2, 1));

            // Define the file path within the 'data' directory
            String filePath = "data/order.xml";

            // Serialize to XML and save to file
            MyUtil.serializeToFile(order, filePath);
            System.out.println("Serialized XML saved to " + filePath);

            // Deserialize from XML file
            Order deserializedOrder = MyUtil.deserializeFromFile(filePath);
            
            // output of the deserialized order
            System.out.println("Deserialized Order:");
            System.out.println(deserializedOrder.toString());

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
