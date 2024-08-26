package poo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyUtil {

    private static final XStream xstream = new XStream(new DomDriver());

    static {
        xstream.addPermission(AnyTypePermission.ANY); // Allow any type to be deserialized
        xstream.processAnnotations(Order.class);      // Ensure Order class is processed for annotations
    }

    public static String serializeOrder(Order order) {
        return xstream.toXML(order);
    }

    public static Order deserializeOrder(String xml) {
        return (Order) xstream.fromXML(xml);
    }

    public static void serializeToFile(Order order, String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());  // Create 'data' directory if it doesn't exist
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(serializeOrder(order));
            }
            System.out.println("Serialized XML saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Order deserializeFromFile(String filePath) {
        try {
            return deserializeOrder(Files.readString(Path.of(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
