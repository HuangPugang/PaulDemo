package demo.hpg.org.pauldemo.getmessage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import demo.hpg.org.pauldemo.R;

/**
 * Created by pau on 3/11/15.
 */
public class MessageActivity extends Activity {

    private Button sendBroadcast;
    private String BROAD_CAST_ACTION="android.provider.Telephony.SMS_RECEIVED";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        sendBroadcast= (Button) findViewById(R.id.sendBroadcast);
        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(BROAD_CAST_ACTION);
                sendBroadcast(intent);
            }
        });

    }
}
