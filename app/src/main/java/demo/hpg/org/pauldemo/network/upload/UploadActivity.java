package demo.hpg.org.pauldemo.network.upload;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/24 16:21
 * PackageName demo.hpg.org.pauldemo.upload
 */
public class UploadActivity extends Activity {
    private Button button;
    public static final String PATH = android.os.Environment.getExternalStorageDirectory()+"/DCIM/test.png";
//    public static final String REQUEST_URL="http://172.16.40.48:8080/MyServe/servlet/GetNewsList";
    public static final String REQUEST_URL="http://172.16.40.48:8080/MyServe/servlet/GetNewsList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        button = (Button) findViewById(R.id.upload_file);
        final File file = new File(PATH);
        Log.e("tag",file.getName());
        button.setText(file.getName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            new Thread(new NetWork()).start();
                upload("http://192.168.3.3:8080/eat/Form//fileUpload.do",file);
            }
        });
    }
    /**
     * 图片转成string
     *
     * @param bitmap
     * @return
     */
    public static String convertIconToString(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(appicon, Base64.DEFAULT);

    }

    /**
     * string转成bitmap
     *
     * @param st
     */
    public static Bitmap convertStringToIcon(String st)
    {
        // OutputStream out;
        Bitmap bitmap = null;
        try
        {
            // out = new FileOutputStream("/sdcard/aa.jpg");
            byte[] bitmapArray;
            bitmapArray = Base64.decode(st, Base64.DEFAULT);
            bitmap =
                    BitmapFactory.decodeByteArray(bitmapArray, 0,
                            bitmapArray.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return bitmap;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    private void upload(String url ,File file){
        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBuilder builder = new MultipartBuilder()
                .type(MultipartBuilder.FORM);
        builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + "versionName" + "\""),
                RequestBody.create(null, "haha"));
        builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + "apkSize" + "\""),
                RequestBody.create(null, "heihei"));

        RequestBody fileBody = null;
        String fileName = file.getName();
        fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        //TODO 根据文件名设置contentType
        builder.addPart(Headers.of("Content-Disposition",
                        "form-data; name=\"" + "file" + "\"; filename=\"" + fileName + "\""),
                fileBody);
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.e("HPG",response.body().string());
            }
        });
    }
}
