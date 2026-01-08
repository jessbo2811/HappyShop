package ci553.happyshop.login;

public class LoginModel {
    public LoginView loginView;

    public LoginType loginType = LoginType.Customer;
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
        System.out.println("Login pressed!");
    }
    void updateView(){
        loginView.update(loginType);
    }
}
