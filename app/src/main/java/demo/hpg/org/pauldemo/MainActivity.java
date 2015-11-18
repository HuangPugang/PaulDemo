package demo.hpg.org.pauldemo;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import demo.hpg.org.pauldemo.dialog.DialogActivity;
import demo.hpg.org.pauldemo.getmessage.MessageActivity;
import java.io.File;

import demo.hpg.org.pauldemo.anim.AnimationActivity;
import demo.hpg.org.pauldemo.circleindicator.ViewpagerIndicatorActivity;
import demo.hpg.org.pauldemo.file.FileActivity;
import demo.hpg.org.pauldemo.getphoto.GetPhotoActivity;
import demo.hpg.org.pauldemo.getuipush.GetuiPushActivity;
import demo.hpg.org.pauldemo.imagecompress.ImageCompressActivity;
import demo.hpg.org.pauldemo.js.JSActivity;
import demo.hpg.org.pauldemo.lightcontrol.LightControlActivity;
import demo.hpg.org.pauldemo.moveview.MoveViewActivity;
import demo.hpg.org.pauldemo.okhttp.OkHttpActivity;
import demo.hpg.org.pauldemo.slidingfinish.SlidingActivity;
import demo.hpg.org.pauldemo.sugarorm.SugarActivity;
import demo.hpg.org.pauldemo.swiperefresh.SwipeRefreshActivity;
import demo.hpg.org.pauldemo.tanxingscrollview.ElasticScrollViewActivity;
import demo.hpg.org.pauldemo.upload.UploadActivity;
import demo.hpg.org.pauldemo.volley.VolleyActivity;
import demo.hpg.org.pauldemo.webview.MyWebview;
//import demo.hpg.org.pauldemo.wifimanager.WifiApActivity;
import demo.hpg.org.pauldemo.wifimanager.WifiManagerActivity;


/**
 * 主程序入口
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button lightControlButton;//亮度调节

    private Button getPhotoButton;//获取图片

    private Button volleyButton;//volley网络请求

    private Button getuiButton; //个推推送

    private Button sugarButton;// sugar orm

    private Button myscrollButton;// 自定义scrollview

    private Button swiperefreshButton;//swiperefresh

    private Button sendBroadcastButton;//发送广播

    private Button indicatorButton ;//指示器

    private Button imgCompressButton;//图片压缩

    private Button wifiButton;//wifi

    private Button wifiApButton;//wifi 热点

    private Button socketButton;//socket

    private Button animButton;//anim动画

    private Button webButton;//webview

    private Button uploadButton; //上传文件

    private Button elasticButton;//弹性scrollview

    private Button slidingFinishButton;//滑动关闭

    private Button jsButton;//js调用

    private Button dialogButton;

    private Button okhttpButton;
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

        myscrollButton = (Button) findViewById(R.id.myscrollview);
        myscrollButton.setOnClickListener(this);

        swiperefreshButton = (Button) findViewById(R.id.swiperefresh);
        swiperefreshButton.setOnClickListener(this);

        sendBroadcastButton = (Button) findViewById(R.id.sendBroadcast);
        sendBroadcastButton.setOnClickListener(this);

        indicatorButton = (Button) findViewById(R.id.indicator_);
        indicatorButton.setOnClickListener(this);

        imgCompressButton= (Button) findViewById(R.id.image_compress);
        imgCompressButton.setOnClickListener(this);

        wifiButton  = (Button) findViewById(R.id.wifi);
        wifiButton.setOnClickListener(this);

        wifiApButton = (Button) findViewById(R.id.wifi_ap);
        wifiApButton.setOnClickListener(this);

        socketButton = (Button) findViewById(R.id.socket);
        socketButton.setOnClickListener(this);

        animButton = (Button) findViewById(R.id.anim);
        animButton.setOnClickListener(this);

        webButton = (Button) findViewById(R.id.webview);
        webButton.setOnClickListener(this);

        uploadButton = (Button) findViewById(R.id.upload);
        uploadButton.setOnClickListener(this);

        elasticButton = (Button) findViewById(R.id.elastic);
        elasticButton.setOnClickListener(this);

        slidingFinishButton = (Button) findViewById(R.id.slidingfinish);
        slidingFinishButton.setOnClickListener(this);

        jsButton = (Button) findViewById(R.id.js);
        jsButton.setOnClickListener(this);

        dialogButton = (Button) findViewById(R.id.dialog);
        dialogButton.setOnClickListener(this);

        okhttpButton = (Button) findViewById(R.id.okhttp);
        okhttpButton.setOnClickListener(this);
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
            case R.id.myscrollview:
                intent.setClass(this, MoveViewActivity.class);
                break;
            case R.id.swiperefresh:
                intent.setClass(this, SwipeRefreshActivity.class);
                break;
            case R.id.sendBroadcast:
                intent.setClass(this, MessageActivity.class);
                break;

            case R.id.indicator_:
                intent.setClass(this, ViewpagerIndicatorActivity.class);
                break;

            case R.id.image_compress:
                intent.setClass(this, ImageCompressActivity.class);
                break;

            case R.id.wifi:
                intent.setClass(this, WifiManagerActivity.class);
                break;

            case R.id.wifi_ap:
//                intent.setClass(this, WifiApActivity.class);
                break;
            case R.id.socket:
                intent.setClass(this, FileActivity.class);
                break;
            case R.id.anim:
                intent.setClass(this, AnimationActivity.class);
                break;
            case R.id.upload:
                intent.setClass(this, UploadActivity.class);
                break;
            case R.id.elastic:
                intent.setClass(this, ElasticScrollViewActivity.class);
                break;
            case R.id.slidingfinish:
                intent.setClass(this, SlidingActivity.class);
                break;
            case R.id.js:
                intent.setClass(this, JSActivity.class);
                break;

            case R.id.webview:
                intent.setClass(this, MyWebview.class);
                break;

            case R.id.dialog:
                intent.setClass(this, DialogActivity.class);
                break;
            case R.id.okhttp:
                intent.setClass(this, OkHttpActivity.class);
                break;
        }
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_in, R.anim.hold);
    }
}