package ci553.happyshop.login;

import ci553.happyshop.utility.UIStyle;
import ci553.happyshop.utility.WinPosManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        Label laPageTitle = new Label("Login");
        laPageTitle.setStyle(UIStyle.labelTitleStyle);

        VBox loginPage = new VBox(15, laPageTitle);
        loginPage.setAlignment(Pos.TOP_CENTER);
        loginPage.setStyle("-fx-padding: 15px");
        return loginPage;
    }

    void update(LoginType loginType){
        switch (loginType){
            case LoginType.Customer:
                System.out.println("CustomerLogin");
            case LoginType.Staff:
                System.out.println("StaffLogin");
        }
    }
}
