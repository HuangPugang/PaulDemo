package demo.hpg.org.pauldemo.upload;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/24 16:21
 * PackageName demo.hpg.org.pauldemo.upload
 */
public class UploadActivity extends Activity {
    private Button button;
    public static final String PATH = android.os.Environment.getExternalStorageDirectory()+"/DCIM/test.jpg";
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
            new Thread(new NetWork()).start();
            }
        });
    }
}
