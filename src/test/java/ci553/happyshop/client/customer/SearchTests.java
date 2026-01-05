package ci553.happyshop.client.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ci553.happyshop.catalogue.Product;
import ci553.happyshop.storageAccess.DatabaseRW;
import ci553.happyshop.storageAccess.DatabaseRWFactory;
import java.util.concurrent.CountDownLatch;

public class SearchTests {

    @Test //Test 3
    void Test() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1); //Latch to wait for thread to finish

        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException ignored) {}

        Platform.runLater(() -> {
            try {
                System.out.println("Inside JavaFX thread");
                RunTestsInJavaFX();
                System.out.println("Finished JavaFX logic");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        });

        latch.await(); // WAIT for test thread to complete
        System.out.println("Tests complete");
    }
    private void RunTestsInJavaFX() throws SQLException{

        //Initialize Customer client MVC
        CustomerView cusView = new CustomerView();
        CustomerController cusController = new CustomerController();
        CustomerModel cusModel = new CustomerModel();
        DatabaseRW databaseRW = DatabaseRWFactory.createDatabaseRW();

        //Link
        cusView.cusController = cusController;
        cusController.cusModel = cusModel;
        cusModel.cusView = cusView;
        cusModel.databaseRW = databaseRW;
        cusView.start(new Stage());


        //Search by ID
        cusView.tfId = new TextField("0001");
        Product out = DoSearch(cusModel);
        assertEquals(out.getProductId(), "0001");
        cusView.tfId = new TextField("");

        cusView.tfName = new TextField("TV");
        out = DoSearch(cusModel);
        assertEquals(out.getProductDescription(), "40 inch TV");


    }
    Product DoSearch(CustomerModel cusModel) throws SQLException{
        
        cusModel.search();
        try{
            Field productField = CustomerModel.class.getDeclaredField("theProduct");
            productField.setAccessible(true);
            Product prod = (Product) productField.get(cusModel);
            return prod;
        }
        catch (Exception e){
            e.printStackTrace();
            assertTrue(false);
            return null;
        }
    }
}
