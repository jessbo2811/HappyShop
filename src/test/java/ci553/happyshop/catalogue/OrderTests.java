package ci553.happyshop.catalogue;
import java.util.ArrayList;
import ci553.happyshop.orderManagement.OrderState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTests {


    @Test //Test 1
    void OrderConstructor(){
        int orderID = 5;
        OrderState state = OrderState.Collected;
        String orderedTime = "000";
        ArrayList<Product> products = new ArrayList<>();
        Order myOrder = new Order(orderID, state, orderedTime, products);
        assertNotNull(myOrder);
        assertEquals(myOrder.getOrderId(), orderID);
        assertEquals(myOrder.getState(), state);
        assertEquals(myOrder.getOrderedDateTime(), orderedTime);
        assertEquals(myOrder.getProductList(), products);
    }
}
