package demo.hpg.org.pauldemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Author huarizhong
 * Date 2015/3/11
 * Time 10:46
 */
public class MyApplication extends Application {

    private static RequestQueue mRequestQueue ;
    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(this);
    }

    public static RequestQueue getRequestQueue(){

        return mRequestQueue;
    }
}
