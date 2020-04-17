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
        signals = new MutableLiveData<>();
        requestMy = new RequestMy(null, null);
    }

    public LiveData<List<Signal>> getSignals() {
    return signals;
    }

    public void setLogin(String login, String password) {
        requestMy.setLogin(login);
        requestMy.setPassword(password);
        loadSignals();
    }

    private void loadSignals() {
        if (!tokenClass.isTokenValid())
            api.getToken(requestMy).observeForever(new Observer<String>() {
                @Override
                public void onChanged(String tokenReponse) {
                    token = tokenReponse;
                    if (token != null)
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

    public String getLogin() {
        return requestMy.getLogin();
    }

}
