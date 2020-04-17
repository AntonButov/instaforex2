package pro.butovanton.instaforex_2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

public class IViewModel extends AndroidViewModel {

    private String login, password;
    private MutableLiveData<List<Signal>> signals;
    private TokenClass tokenClass;
    private Api api;
    private RequestMy requestMy;
    private String token;

    public IViewModel(@NonNull Application application) {
        super(application);
        tokenClass = new TokenClass(application);
        api = new Api();
        requestMy = new RequestMy();
    }

    public LiveData<List<Signal>> getSignals() {
        signals = new MutableLiveData<>();
    return signals;
    }

    public void setLogin(String login, String password) {
        requestMy.setLogin(login);
        requestMy.setPassword(password);
        if (!tokenClass.isTokenValid())
            api.getToken(requestMy).observeForever(new Observer<String>() {
                @Override
                public void onChanged(String tokenReponse) {
                    token = tokenReponse;
                    responseSignals(token);
                }
            });
        else {
            token = tokenClass.loadToken();
            responseSignals(token);
        }
    }

    private void responseSignals(String token) {
        api.getAnaliticsAsinh(token, requestMy.getLogin()).observeForever(new Observer<List<Signal>>() {
            @Override
            public void onChanged(List<Signal> signalsRes) {
                signals.setValue(signalsRes);
            }
        });
    }
}
