package ci553.happyshop.client.customer;

import java.lang.reflect.Field;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ci553.happyshop.catalogue.Product;
import javafx.application.Platform;
import javafx.scene.control.TextField;

public class CustomerModelTests {

//This starts JavaFX so we can use controls ect to be passed in to the customer model
    @BeforeAll
    static void initToolkit() throws InterruptedException {
        Thread t = new Thread(() -> Platform.startup(() -> {}));
        t.setDaemon(true);
        t.start();
        t.join();
    }
    @Test
    void SearchByNameTest() throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        Platform.runLater(() -> {
            try {
                CustomerClient client = new CustomerClient();
                CustomerModel model = client.TestStart();
                
                // Safe to create TextField now
                model.cusView.tfName = new TextField("USB");
                model.search();
                
                Field productField = CustomerModel.class.getDeclaredField("TheProduct");
                productField.setAccessible(true);
                Product castSearchResult = (Product) productField.get(model);
                
                assertEquals("0007", castSearchResult.getProductId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
