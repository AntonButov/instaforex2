package pro.butovanton.instaforex_2;

import android.app.Application;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.preference.PreferenceManager;

public class TokenClass {
private SharedPreferences msharedPreferences;
final long TIMEOK = 5400;

public TokenClass(Application application) {
    msharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
}

public boolean isTokenValid () {
   Long tokenTime = loadTokenTime();
   Long time = System.currentTimeMillis() / 1000L;
   return time - tokenTime < TIMEOK;
}

private Long loadTokenTime() {
    Long tokenTime;
    tokenTime = msharedPreferences.getLong("tokenTime", 0);
    return  tokenTime;
}

public String loadToken() {
    String token = "";
        token = msharedPreferences.getString("token", "");
    return  token;
}

public void saveToken(String token) {
    SharedPreferences.Editor editor = msharedPreferences.edit();
    editor.putString("token",token);
    long unixTime = System.currentTimeMillis() / 1000L;
    editor.putLong("tokenTime",unixTime);
    editor.commit();
}


}
