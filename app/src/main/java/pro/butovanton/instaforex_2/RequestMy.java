package pro.butovanton.instaforex_2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestMy {

    public RequestMy() {

    }

    String Login;
    String Password;

    public void setLogin(String login) {
        Login = login;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

