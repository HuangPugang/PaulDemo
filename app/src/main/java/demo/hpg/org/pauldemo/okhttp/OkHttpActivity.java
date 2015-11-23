package demo.hpg.org.pauldemo.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
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

//        okHttpGet();
//        postFile();
//        okHttpPost();
        findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileUpload();
//                try {
//                    run();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
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
                Log.e("HPG", response.body().toString());
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



    private void okHttpPost() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("username", "android")
                .add("password", "bug")
                .build();

        Request request = new Request.Builder()
                .url("http://192.168.3.3:8080/eat/User/login.do")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.e("HPG", response.body().string());
            }
        });

    }


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    private void fileUpload(){
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = new File(android.os.Environment.getExternalStorageDirectory()+"/DCIM/test.jpg");
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(Headers.of(
                                "Content-Disposition",
                                "form-data; name=\"username\""),
                        RequestBody.create(null, "张鸿洋"))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"file\";filename=\"test.jpg\""), fileBody)
                .build();

        Request request = new Request.Builder()
                .url("http://192.168.3.3:8080/eat/Post/write.do?title=test&content=neirong&lat=13j")
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("HPG","onFailure");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                    Log.e("HPG",response.body().string());
            }
        });
    }
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    public void run() throws Exception {
        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"title\""),
                        RequestBody.create(null, "Square Logo"))
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"file\""),
                        RequestBody.create(MEDIA_TYPE_PNG, new File(android.os.Environment.getExternalStorageDirectory()+"/DCIM/test.jpg")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + "11")
                .url("http://192.168.3.3:8080/eat/User/upload.do")
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("HPG","onFailure");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.e("HPG",response.body().string());
            }
        });
    }

}
