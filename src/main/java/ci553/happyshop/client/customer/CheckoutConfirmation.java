package ci553.happyshop.client.customer;

import ci553.happyshop.utility.WinPosManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

//initializes and manages its own window to confirm a checkout on the customer view that created it
public class CheckoutConfirmation {
    Stage window;
    CustomerView cusView;
    public CheckoutConfirmation(CustomerView view){
        cusView = view;
        window = new Stage();
        Button button = new Button("Confirm checkout");
        button.setOnAction(e -> confirm());
        Scene scene = new Scene(button);

        window.setScene(scene);
        WinPosManager.registerWindow(window, 100, 200);
        System.out.println("Creating checkout confirmation box");
        window.show();
    }
    private void confirm(){
        try{
            cusView.confirmCheckout();
            window.hide();
        }
        catch (Exception e){
            return;
        }    
    }
}
