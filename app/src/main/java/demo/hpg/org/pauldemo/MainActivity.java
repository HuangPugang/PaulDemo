package demo.hpg.org.pauldemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import demo.hpg.org.pauldemo.dialog.DialogActivity;
import demo.hpg.org.pauldemo.getmessage.MessageActivity;

import demo.hpg.org.pauldemo.anim.AnimationActivity;
import demo.hpg.org.pauldemo.imagecache.ImageCacheActivity;
import demo.hpg.org.pauldemo.view.circleindicator.ViewpagerIndicatorActivity;
import demo.hpg.org.pauldemo.file.FileActivity;
import demo.hpg.org.pauldemo.getphoto.GetPhotoActivity;
import demo.hpg.org.pauldemo.getuipush.GetuiPushActivity;
import demo.hpg.org.pauldemo.imagecompress.ImageCompressActivity;
import demo.hpg.org.pauldemo.js.JSActivity;
import demo.hpg.org.pauldemo.lightcontrol.LightControlActivity;
import demo.hpg.org.pauldemo.view.moveview.MoveViewActivity;
import demo.hpg.org.pauldemo.okhttp.OkHttpActivity;
import demo.hpg.org.pauldemo.view.requestlayout.RequestLayoutActvity;
import demo.hpg.org.pauldemo.view.requestlayout.RequestView;
import demo.hpg.org.pauldemo.view.slidingfinish.SlidingActivity;
import demo.hpg.org.pauldemo.sugarorm.SugarActivity;
import demo.hpg.org.pauldemo.view.swiperefresh.SwipeRefreshActivity;
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
    private Button requestButton;
    private Button imageCacheButton;

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

        requestButton = (Button) findViewById(R.id.request);
        requestButton.setOnClickListener(this);

        imageCacheButton = (Button) findViewById(R.id.imagecache);
        imageCacheButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.light_control:
                openActivity(LightControlActivity.class);
                break;
            case R.id.getphoto:
                openActivity(GetPhotoActivity.class);
                break;
            case R.id.volley:
                openActivity(VolleyActivity.class);
                break;
            case R.id.getui:
                openActivity(GetuiPushActivity.class);
                break;
            case R.id.sugar:
                openActivity(SugarActivity.class);
                break;
            case R.id.myscrollview:
                openActivity(MoveViewActivity.class);
                break;
            case R.id.swiperefresh:
                openActivity(SwipeRefreshActivity.class);
                break;
            case R.id.sendBroadcast:
                openActivity(MessageActivity.class);
                break;

            case R.id.indicator_:
                openActivity(ViewpagerIndicatorActivity.class);
                break;

            case R.id.image_compress:
                openActivity(ImageCompressActivity.class);
                break;

            case R.id.wifi:
                openActivity(WifiManagerActivity.class);
                break;

            case R.id.wifi_ap:
//                intent.setClass(this, WifiApActivity.class);
                break;
            case R.id.socket:
                openActivity(FileActivity.class);
                break;
            case R.id.anim:
                openActivity(AnimationActivity.class);
                break;
            case R.id.upload:
                openActivity(UploadActivity.class);
                break;
            case R.id.elastic:
                openActivity(ElasticScrollViewActivity.class);
                break;
            case R.id.slidingfinish:
                openActivity(SlidingActivity.class);
                break;
            case R.id.js:
                openActivity( JSActivity.class);
                break;

            case R.id.webview:
                openActivity( MyWebview.class);
                break;

            case R.id.dialog:
                openActivity( DialogActivity.class);
                break;
            case R.id.okhttp:
                openActivity( OkHttpActivity.class);
                break;
            case R.id.request:
                openActivity( RequestLayoutActvity.class);
                break;

            case R.id.imagecache:
                openActivity( ImageCacheActivity.class);
                break;
        }
//        startActivity(intent);
//        overridePendingTransition(R.anim.bottom_in, R.anim.hold);
    }



    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);

    }
}