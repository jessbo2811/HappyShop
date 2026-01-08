package ci553.happyshop.login;

import javafx.application.Application;
import javafx.stage.Stage;

public class LoginClient extends Application{
    public static void main(String[] args) {
        launch(args);
    }

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
