package pro.butovanton.instaforex_2;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface JSONPlaceHolderApi {

    @POST("api/Authentication/RequestMoblieCabinetApiToken")
    Call<String> putLogin(@Body RequestMy requestMy);

    @GET("clientmobile/GetAnalyticSignals/20234561?tradingsystem=3")
    Call<List<Signal>> getAnaliticSignal(@Header("passkey") String token, @Query("pairs") String piars, @Query("from") int from, @Query("to") int to);

}