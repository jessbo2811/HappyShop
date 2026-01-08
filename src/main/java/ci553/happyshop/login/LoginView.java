package ci553.happyshop.login;

import javafx.stage.Stage;

public class LoginView {
    public LoginController loginController;




    public void start(Stage window) {
    }

    void setLoginType(LoginType loginType){
        switch (loginType){
            case LoginType.Customer:
                System.out.println("CustomerLogin");
            case LoginType.Staff:
                System.out.println("StaffLogin");
        }
    }
}
