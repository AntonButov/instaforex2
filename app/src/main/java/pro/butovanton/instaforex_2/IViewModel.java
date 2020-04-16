package pro.butovanton.instaforex_2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class IViewModel extends AndroidViewModel {

    private String login, password;
    private LiveData<List<Signal>> signals;
    private Application application;

    public IViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public void setLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
