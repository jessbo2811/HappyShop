package ci553.happyshop.login;

public class LoginModel {
    public LoginView loginView;

    public LoginType loginType = LoginType.Customer;


    void updateView(){
        loginView.setLoginType(loginType);
    }
}
