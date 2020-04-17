package pro.butovanton.instaforex_2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestMy {

    String Login;
    String Password;

    public RequestMy(String login, String password) {
        setLogin(login);
        setPassword(password);
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getLogin() {
        return Login;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

