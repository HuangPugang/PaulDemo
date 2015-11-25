package demo.hpg.org.pauldemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import demo.hpg.org.pauldemo.base.BaseActivity;
import demo.hpg.org.pauldemo.view.clip.ClipActivity;
import demo.hpg.org.pauldemo.view.dialog.DialogActivity;
import demo.hpg.org.pauldemo.system.getmessage.MessageActivity;

import demo.hpg.org.pauldemo.anim.AnimationActivity;
import demo.hpg.org.pauldemo.imagecache.ImageCacheActivity;
import demo.hpg.org.pauldemo.view.circleindicator.ViewpagerIndicatorActivity;
import demo.hpg.org.pauldemo.system.file.FileActivity;
import demo.hpg.org.pauldemo.system.getphoto.GetPhotoActivity;
import demo.hpg.org.pauldemo.getuipush.GetuiPushActivity;
import demo.hpg.org.pauldemo.imagecompress.ImageCompressActivity;
import demo.hpg.org.pauldemo.js.JSActivity;
import demo.hpg.org.pauldemo.system.lightcontrol.LightControlActivity;
import demo.hpg.org.pauldemo.view.moveview.MoveViewActivity;
import demo.hpg.org.pauldemo.network.okhttp.OkHttpActivity;
import demo.hpg.org.pauldemo.view.nostatus.NoStatusActivity;
import demo.hpg.org.pauldemo.view.requestlayout.RequestLayoutActivity;
import demo.hpg.org.pauldemo.view.slidingfinish.SlidingActivity;
import demo.hpg.org.pauldemo.sugarorm.SugarActivity;
import demo.hpg.org.pauldemo.view.stickscroll.StickyScrollActivity;
import demo.hpg.org.pauldemo.view.swiperefresh.SwipeRefreshActivity;
import demo.hpg.org.pauldemo.view.tanxingscrollview.ElasticScrollViewActivity;
import demo.hpg.org.pauldemo.network.upload.UploadActivity;
import demo.hpg.org.pauldemo.network.volley.VolleyActivity;
import demo.hpg.org.pauldemo.view.webview.MyWebview;
//import demo.hpg.org.pauldemo.wifimanager.WifiApActivity;
import demo.hpg.org.pauldemo.system.wifimanager.WifiManagerActivity;


/**
 * 主程序入口
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

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
    private Button stickButton;
    private Button clipButton;

    private Button nostatusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewAndOnclick(lightControlButton, R.id.light_control);

        findViewAndOnclick(getPhotoButton, R.id.getphoto);

        findViewAndOnclick(volleyButton, R.id.volley);

        findViewAndOnclick(getuiButton, R.id.getui);

        findViewAndOnclick(sugarButton, R.id.sugar);

        findViewAndOnclick(myscrollButton, R.id.myscrollview);

        findViewAndOnclick(swiperefreshButton, R.id.swiperefresh);

        findViewAndOnclick(sendBroadcastButton, R.id.sendBroadcast);

        findViewAndOnclick(indicatorButton, R.id.indicator_);

        findViewAndOnclick(imgCompressButton, R.id.image_compress);

        findViewAndOnclick(wifiButton, R.id.wifi);

        findViewAndOnclick(wifiApButton, R.id.wifi_ap);

        findViewAndOnclick(socketButton, R.id.socket);

        findViewAndOnclick(animButton, R.id.anim);

        findViewAndOnclick(webButton, R.id.webview);

        findViewAndOnclick(uploadButton, R.id.upload);

        findViewAndOnclick(elasticButton, R.id.elastic);

        findViewAndOnclick(slidingFinishButton, R.id.slidingfinish);

        findViewAndOnclick(jsButton, R.id.js);

        findViewAndOnclick(dialogButton, R.id.dialog);

        findViewAndOnclick(okhttpButton, R.id.okhttp);

        findViewAndOnclick(requestButton, R.id.request);

        findViewAndOnclick(imageCacheButton, R.id.imagecache);

        findViewAndOnclick(stickButton,R.id.stick);

        findViewAndOnclick(clipButton,R.id.clip);

        findViewAndOnclick(nostatusButton,R.id.nostatus);
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
                openActivity(JSActivity.class);
                break;

            case R.id.webview:
                openActivity(MyWebview.class);
                break;

            case R.id.dialog:
                openActivity(DialogActivity.class);
                break;
            case R.id.okhttp:
                openActivity(OkHttpActivity.class);
                break;
            case R.id.request:
                openActivity(RequestLayoutActivity.class);
                break;

            case R.id.imagecache:
                openActivity(ImageCacheActivity.class);
                break;
            case R.id.stick:
                openActivity(StickyScrollActivity.class);
                break;
            case R.id.clip:
                openActivity(ClipActivity.class);
                break;
            case R.id.nostatus:
                openActivity(NoStatusActivity.class);
                break;

        }
    }

    private void findViewAndOnclick(Button button,int resourceId){
        button = fvById(resourceId);
        button.setOnClickListener(this);
    }

}