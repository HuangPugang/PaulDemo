package demo.hpg.org.pauldemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import demo.hpg.org.pauldemo.getphoto.GetPhotoActivity;
import demo.hpg.org.pauldemo.getuipush.GetuiPushActivity;
import demo.hpg.org.pauldemo.lightcontrol.LightControlActivity;
import demo.hpg.org.pauldemo.sugarorm.SugarActivity;
import demo.hpg.org.pauldemo.volley.VolleyActivity;


/**
 * 主程序入口
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button lightControlButton;//亮度调节

    private Button getPhotoButton;//获取图片

    private Button volleyButton;//volley网络请求

    private Button getuiButton; //个推推送

    private Button sugarButton;// sugar orm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        lightControlButton = (Button) findViewById(R.id.light_control);
        lightControlButton.setOnClickListener(this);

        getPhotoButton = (Button) findViewById(R.id.getphoto);
        getPhotoButton.setOnClickListener(this);


        volleyButton = (Button) findViewById(R.id.volley);
        volleyButton.setOnClickListener(this);

        getuiButton = (Button) findViewById(R.id.getui);
        getuiButton.setOnClickListener(this);

        sugarButton = (Button) findViewById(R.id.sugar);
        sugarButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.light_control:
                intent.setClass(this, LightControlActivity.class);
                break;
            case R.id.getphoto:
                intent.setClass(this, GetPhotoActivity.class);
                break;
            case R.id.volley:
                intent.setClass(this, VolleyActivity.class);
                break;
            case R.id.getui:
                intent.setClass(this, GetuiPushActivity.class);
                break;
            case R.id.sugar:
                intent.setClass(this, SugarActivity.class);
                break;

        }
        startActivity(intent);
    }
}