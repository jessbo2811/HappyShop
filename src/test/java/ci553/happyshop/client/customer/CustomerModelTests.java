package ci553.happyshop.client.customer;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ci553.happyshop.catalogue.Product;
import ci553.happyshop.storageAccess.DatabaseRW;
import ci553.happyshop.storageAccess.DatabaseRWFactory;

public class CustomerModelTests {
    @Test //Test 2
    void SearchByNameTest() throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        CustomerView cusView = new CustomerView();
        CustomerController cusController = new CustomerController();
        CustomerModel cusModel = new CustomerModel();
        DatabaseRW databaseRW = DatabaseRWFactory.createDatabaseRW();

        cusView.cusController = cusController;
        cusController.cusModel = cusModel;
        cusModel.cusView = cusView;
        cusModel.databaseRW = databaseRW;
        
        Product search = cusModel.search("0001", "");
        assertEquals(search.getProductId(), "0001");
        search = cusModel.search("", "TV");
        assertEquals(search.getProductDescription(), "40 inch TV");
    }
}
