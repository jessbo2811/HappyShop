package ci553.happyshop.login;

import java.util.Map;

import ci553.happyshop.client.Main;
import javafx.event.Event;

import java.util.HashMap;

public class LoginModel {
    public LoginView loginView;

    public LoginType loginType = LoginType.Customer;

    private boolean loggedIn = false;

    Map<String, String> staffLogins;
    Map<String, String> customerLogins;

    private Main main; //a reference to main so we can initialize the application once logged in

    public void setUpLogins(Main main){
        this.main = main;
        staffLogins = new HashMap<>();
        customerLogins = new HashMap<>();
        staffLogins.put("Jess", "Bryant");
        customerLogins.put("Customer1", "Pass1");
    }
    void switchLoginType(){
        if (loginType == LoginType.Customer){
            loginType = LoginType.Staff;
        }
        else{
            loginType = LoginType.Customer;
        }
        updateView();
    }
    void loginPressed(){
        String userInput = loginView.tfUsername.getText();
        String passInput = loginView.tfPassword.getText();
        if (attemptLogin(userInput, passInput)){
            loggedIn = true;
            if (loginType == LoginType.Customer){
                main.loggedInCustomer();
            }
            else{
                main.loggedInStaff();
            }
            updateView();
        }
    }
    private boolean attemptLogin(String username, String password){
        if (loginType == LoginType.Customer){
            if(!customerLogins.containsKey(username)){
                return false;
            }
            if (customerLogins.get(username).equals(password)){
                return true;
            }
            return false;
        }
        else{
            if (!staffLogins.containsKey(username)){
                return false;
            }
            if (staffLogins.get(username).equals(password)){
                return true;
            }
            return false;
        }
    }
    void updateView(){
        loginView.update(loginType, loggedIn);
    }
}
