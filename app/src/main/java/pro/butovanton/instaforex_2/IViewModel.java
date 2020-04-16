package pro.butovanton.instaforex_2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class IViewModel extends AndroidViewModel {

    private final String login, password;
    private LiveData<List<Signal>> mAllWords;
    private Application application;

    public IViewModel(@NonNull Application application, String login, String password) {
        super(application);
        this.application = application;
        this.login = login;
        this.password = password;
    }

}
