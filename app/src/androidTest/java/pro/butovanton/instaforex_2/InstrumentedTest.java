package pro.butovanton.instaforex_2;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

     Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
     Api api = new Api();
     TokenClass tokenClass = new TokenClass((Application) appContext.getApplicationContext());

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

       String  token = "";


    @Before
    public void Api_getToken() {
        if (tokenClass.isTokenValid())
            Log.d("DEBUG", "token is valid");
        else {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            RequestMy requestMy = new RequestMy("20234561", "ladevi31");
            api.getToken(requestMy).observeForever(new Observer<String>() {
                @Override
                public void onChanged(String responce) {
                    token = responce;
                    assertTrue(token.length() > 0);
                    Log.d("DEBUG", "Token " + token);
                    assertNotNull(token);
                    tokenClass.saveToken(token);
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getAnaliticAsinch() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(7);
        String token = tokenClass.loadToken();
        api.getAnaliticsAsinh(token, "20234561").observeForever(new Observer<List<Signal>>() {
            @Override
            public void onChanged(List<Signal> signals) {
                assertNotNull(signals);
                count.countDown();
            }
        });
        count.await();
    }
}
