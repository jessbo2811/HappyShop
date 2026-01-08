package ci553.happyshop.login;

public class LoginController {
    public LoginModel loginModel;

    public void doAction(String action){
        switch (action){
            case "SwitchLogin":
                loginModel.switchLoginType();
            case "LoginPressed":
                loginModel.loginPressed();
        }
    }
}
