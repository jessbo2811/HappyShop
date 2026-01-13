package ci553.happyshop.login;

import java.util.Map;

import ci553.happyshop.client.Main;

import java.util.HashMap;
//The model of the login feature - manages the logic of logging in, tracks logins, and tracks if the user has logged in yet
public class LoginModel {
    public LoginView loginView;

    public LoginType loginType = LoginType.Customer;

    private boolean loggedIn = false;

    Map<String, String> staffLogins;
    Map<String, String> customerLogins;

    private Main main; //a reference to main so we can initialize the application once logged in

    /** 
     * Creates test logins with example names and passwords to allow the system to be tested without a full logins database
     * @param main A reference to the main class to allow the the rest of the app to be launched once the user has logged in
     */
    public void setUpLogins(Main main){
        this.main = main;
        staffLogins = new HashMap<>();
        customerLogins = new HashMap<>();
        staffLogins.put("Jess", "Bryant");
        customerLogins.put("Customer1", "Pass1");
    }
    /**
     * Switches between customer and staff login mode
     */
    void switchLoginType(){
        if (loginType == LoginType.Customer){
            loginType = LoginType.Staff;
        }
        else{
            loginType = LoginType.Customer;
        }
        updateView();
    }
    /**
     * Called when login is pressed in the LoginView, extracts entered username and password from view and calls attempt login
     */
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
    /** 
     * @param username the username for the attempted login
     * @param password the password for the attempted login
     * @return boolean wether or not the attempt was successful
     */
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
    /**
     * Updates the connected view with the selected type, and wether or not the log in has been completed
     */
    void updateView(){
        loginView.update(loginType, loggedIn);
    }
}
