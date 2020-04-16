package pro.butovanton.instaforex_2;

import android.os.AsyncTask;
import android.util.ArraySet;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import okhttp3.ResponseBody;
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
        pairs.add("EURUSD"); pairs.add("GBPUSD"); pairs.add("USDJPY"); pairs.add("USDCHF"); pairs.add("USDCAD"); pairs.add("AUDUSD"); pairs.add("NZDUSD");
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
                    token.setValue(response.body());
                     }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
   return token;
   }

   public LiveData<List<Signal>> getAnaliticsAsinh(final String token) {
       new AsyncTask<Void, Void, List<Signal>>() {
           @Override
           protected List<Signal> doInBackground(Void... voids) {
               List<Signal> signals = new ArrayList<>();
               for (String pair : pairs) {
                   int now = (int) (System.currentTimeMillis() / 1000L);
                   try {
                       List<Signal> signalsall = jsonPlaceHolderApi.getAnaliticSignal(token, pair, now - 20 * DAY, now ).execute().body();
                       Signal signal = signalsall.get(signalsall.size()-1);
                       signals.add(signal);
                       Log.d("DEBUG", "pair: " + signal.pair);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
               return signals;
           }

           @Override
           protected void onPostExecute(List<Signal> signalss) {
               super.onPostExecute(signalss);
               signals.setValue(signalss);
           }
       }.execute();
        return signals;
   }

    public LiveData<List<Signal>>  getAnalitic(String token) {
        int now = (int) (System.currentTimeMillis() / 1000L);
        jsonPlaceHolderApi.getAnaliticSignal(token, "EURUSD,GBPUSD,USDJPY,USDCHF,USDCAD,AUDUSD,NZDUSD", now - 20* DAY, now).enqueue(new Callback<List<Signal>>() {
            @Override
            public void onResponse(Call<List<Signal>> call, Response<List<Signal>> response) {
                if (response.isSuccessful()) {
                   signals.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Signal>> call, Throwable t) {

            }
        });
        return signals;
    }

}












