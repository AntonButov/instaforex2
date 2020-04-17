package pro.butovanton.instaforex_2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class IViewModel extends AndroidViewModel {

    private String login, password;
    private MutableLiveData<List<Signal>> signals;

    public IViewModel(@NonNull Application application) {
        super(application);
        TokenClass tokenClass = new TokenClass(application);
        Api api = new Api();
    }

    public void setLogin(String login, String password) {
        this.login = login;
        this.password = password;

    }

    public LiveData<List<Signal>> getSignals() {
        signals = new MutableLiveData<>();
    return signals;
    }
}
