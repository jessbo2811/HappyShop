package ci553.happyshop.login;

public class LoginController {
    public LoginModel loginModel;

    /** 
     * @param action
     */
    public void doAction(String action){
        switch (action){
            case "SwitchLogin":
                loginModel.switchLoginType();
                break;
            case "LoginPressed":
                loginModel.loginPressed();
                break;
        }
    }
}