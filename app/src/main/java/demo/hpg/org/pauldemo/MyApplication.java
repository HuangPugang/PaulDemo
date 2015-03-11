package demo.hpg.org.pauldemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import demo.hpg.org.sugar.orm.SugarContext;

/**
 * Author huarizhong
 * Date 2015/3/11
 * Time 10:46
 */
public class MyApplication extends Application {

    //volley请求队列
    private static RequestQueue mRequestQueue ;
    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(this);

        //初始化sugarorm
        SugarContext.init(this);
    }

    /**
     * 获得volley请求队列
     * @return
     */
    public static RequestQueue getRequestQueue(){
        return mRequestQueue;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
