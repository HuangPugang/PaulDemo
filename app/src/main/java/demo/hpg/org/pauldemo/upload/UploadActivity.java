package demo.hpg.org.pauldemo.upload;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/24 16:21
 * PackageName demo.hpg.org.pauldemo.upload
 */
public class UploadActivity extends Activity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        button = (Button) findViewById(R.id.upload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
