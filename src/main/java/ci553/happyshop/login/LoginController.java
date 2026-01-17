package ci553.happyshop.login;

/**
 * The controller component for the login feature - allows the view to execute functionality within the model
*/
public class LoginController {
    public LoginModel loginModel;

    /** 
     * @param action the action to execute within the model
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