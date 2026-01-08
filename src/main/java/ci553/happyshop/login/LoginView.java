package ci553.happyshop.login;

import ci553.happyshop.utility.UIStyle;
import ci553.happyshop.utility.WinPosManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    public LoginController loginController;

    private final int WIDTH = UIStyle.loginMinWidth;
    private final int HEIGHT = UIStyle.loginMinHeight;

    private VBox root;
    TextField tfUsername;
    TextField tfPassword;
    private Button toggleLogin;
    private Label laPageTitle;

    private Stage viewWindow;

    public void start(Stage window) {
        VBox loginPage = createLoginPage();
        root = new VBox(10, loginPage);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle(UIStyle.rootStyle);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        window.setScene(scene);
        window.setTitle("HappyShop Login");
        WinPosManager.registerWindow(window, WIDTH, HEIGHT);
        window.show();
        viewWindow = window;
    }
    private VBox createLoginPage(){
        laPageTitle = new Label("Customer Login");
        laPageTitle.setStyle(UIStyle.labelTitleStyle);

        toggleLogin = new Button("Switch to staff login");
        toggleLogin.setStyle(UIStyle.blueBtnStyle);
        toggleLogin.setOnAction(e -> loginController.doAction("SwitchLogin"));

        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle(UIStyle.labelStyle);

        tfUsername = new TextField("");
        tfUsername.setStyle(UIStyle.textFiledStyle);

        HBox userSection = new HBox(usernameLabel, tfUsername);

        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle(UIStyle.labelStyle);

        tfPassword = new TextField("");
        tfPassword.setStyle(UIStyle.textFiledStyle);

        HBox passSection = new HBox(passwordLabel, tfPassword);

        Button loginButton = new Button("Login");
        loginButton.setStyle(UIStyle.blueBtnStyle);
        loginButton.setOnAction(e -> loginController.doAction("LoginPressed"));

        VBox loginPage = new VBox(15, laPageTitle, toggleLogin, userSection, passSection, loginButton);
        loginPage.setAlignment(Pos.TOP_CENTER);
        loginPage.setStyle("-fx-padding: 15px");
        return loginPage;
    }

    void update(LoginType loginType, boolean loggedIn){
        if (loggedIn){
            viewWindow.hide();
        }
        switch (loginType){
            case LoginType.Customer:
                laPageTitle.setText("Customer Login");
                toggleLogin.setText("Switch to staff login");
                break;
            case LoginType.Staff:
                laPageTitle.setText("Staff Login");
                toggleLogin.setText("Switch to customer login");
                break;

        }
    }
}
