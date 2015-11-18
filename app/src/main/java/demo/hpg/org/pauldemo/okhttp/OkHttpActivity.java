package demo.hpg.org.pauldemo.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import demo.hpg.org.pauldemo.R;

/**
 * Created by Paul on 15/11/18.
 */
public class OkHttpActivity extends Activity {
    String url = "http://android.dev.bai.ai/apiv2/bizform/movie/hotcinema.json?sign=d619d1be574a298809902e8c05667e7c&searchinfo=%E5%A4%A7%E4%BC%97";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        init();
    }

    private void init(){

        okHttpGet();
    }

    private void okHttpGet(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.e("HPG",response.body().toString());
                Message message = Message.obtain();
                message.obj = response.body().string();
                handler.sendMessage(message);
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(OkHttpActivity.this,msg.obj.toString(),Toast.LENGTH_LONG).show();
        }
    };
}
