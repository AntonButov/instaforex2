package pro.butovanton.instaforex_2;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    public Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    public Api api = new Api();
    public TokenClass tokenClass = new TokenClass((Application) appContext.getApplicationContext());

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void isTokenVolid() {
        assertTrue(tokenClass.isTokenValid());
    }

    String  token = "";

    @Test
    public void Api_getToken() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        api.getToken(new RequestMy()).observeForever(new Observer<String>() {
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

    @Test
    public void getAnaliticAsinch() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(1);
        String token = tokenClass.loadToken();
        api.getAnaliticsAsinh(token).observeForever(new Observer<List<Signal>>() {
            @Override
            public void onChanged(List<Signal> signals) {
                assertTrue(signals.size() == 7);
                count.countDown();
            }
        });
        count.await();
    }
}
