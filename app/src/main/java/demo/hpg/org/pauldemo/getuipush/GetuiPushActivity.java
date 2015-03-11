package demo.hpg.org.pauldemo.getuipush;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.igexin.sdk.PushManager;

import demo.hpg.org.pauldemo.R;

/**
 * 个腿消息推送服务
 * Author huarizhong
 * Date 2015/3/11
 * Time 12:28
 */
public class GetuiPushActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getuipush);
        PushManager.getInstance().initialize(this.getApplicationContext());
    }
}
