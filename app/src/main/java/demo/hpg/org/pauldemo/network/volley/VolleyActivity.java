package demo.hpg.org.pauldemo.network.volley;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/11
 * Time 10:43
 */
public class VolleyActivity extends Activity {
    private Button sendButton;
    private ImageView imageView;
    private String url = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        imageView= (ImageView) findViewById(R.id.image);
        sendButton = (Button) findViewById(R.id.send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageManager.download(url,imageView);
            }

        });
    }
}
