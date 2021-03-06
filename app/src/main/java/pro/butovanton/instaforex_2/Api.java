package pro.butovanton.instaforex_2;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Api {

    final int DAY = 86400;
    final List<String> pairs = new ArrayList<>();

    private NetworkService networkService;
    private JSONPlaceHolderApi jsonPlaceHolderApi;
    private MutableLiveData<String> token;
    private MutableLiveData<List<Signal>> signals;

    public Api() {
        pairs.add("EURUSD"); pairs.add("GBPUSD"); pairs.add("USDJPY"); pairs.add("USDCHF");
        pairs.add("USDCAD"); pairs.add("AUDUSD"); pairs.add("NZDUSD");
        networkService = NetworkService.getInstance();
        jsonPlaceHolderApi = networkService.getJSONApi();
        token = new MutableLiveData<>();
        signals = new MutableLiveData<>();
    }

   public LiveData<String>  getToken(RequestMy requestMy) {
        jsonPlaceHolderApi.putLogin(requestMy).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                    String str = response.body();
                    token.setValue(str);
                     }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
   return token;
   }

   public LiveData<List<Signal>> getAnaliticsAsinh(final String token, final String login) {
       new AsyncTask<Void, Void, Void>() {
           @Override
           protected Void doInBackground(Void... voids) {
               List<Signal> signalsRes = new ArrayList<>();
               List<Signal> signalsall;
               int now = (int) (System.currentTimeMillis() / 1000L);
               for (String pair : pairs) {
                   try {
                       signalsall = jsonPlaceHolderApi.getAnaliticSignal(token, login,3, pair, now - 20 * DAY, now ).execute().body();
                       if (signalsall != null) {
                           Signal signal = signalsall.get(signalsall.size() - 1);
                           signalsRes.add(signal);
                           Log.d("DEBUG", "pair: " + signal.pair);
                           signals.postValue(signalsRes);
                       }
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
               return null;
           }
       }.execute(null,null,null);
        return signals;
   }
}












