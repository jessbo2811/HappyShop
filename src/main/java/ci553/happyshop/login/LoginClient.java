package ci553.happyshop.login;

import javafx.application.Application;
import javafx.stage.Stage;

//Creates and links a standalone login client - cna be used for testing
public class LoginClient extends Application{
    /** 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * @param window The window to create the client inside of
     */
    @Override
    public void start(Stage window) {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController();
        LoginModel loginModel = new LoginModel();

        loginView.loginController = loginController;
        loginController.loginModel = loginModel;
        loginModel.loginView = loginView;
        loginView.start(window);
    }
}
