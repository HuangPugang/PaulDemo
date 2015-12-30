package demo.hpg.org.pauldemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import demo.hpg.org.pauldemo.anim.property.PropertyActivity;
import demo.hpg.org.pauldemo.base.BaseActivity;
import demo.hpg.org.pauldemo.behavior.BehaviorActivity;
import demo.hpg.org.pauldemo.list.ListTestActivity;
import demo.hpg.org.pauldemo.media.autio.AudioActivity;
import demo.hpg.org.pauldemo.media.video.VideoActivity;
import demo.hpg.org.pauldemo.optimize.OptimizeActivity;
import demo.hpg.org.pauldemo.overtime.OverTimeActivity;
import demo.hpg.org.pauldemo.rxjava.RxJavaActivity;
import demo.hpg.org.pauldemo.simplepullloadmore.LoadmoreActivity;
import demo.hpg.org.pauldemo.view.automove.AutoMoveActivity;
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
import demo.hpg.org.pauldemo.view.pulltozoom.PullToZoomActivity;
import demo.hpg.org.pauldemo.view.recycleview.RecycleActivity;
import demo.hpg.org.pauldemo.view.requestlayout.RequestLayoutActivity;
import demo.hpg.org.pauldemo.view.slidingfinish.SlidingActivity;
import demo.hpg.org.pauldemo.sugarorm.SugarActivity;
import demo.hpg.org.pauldemo.view.stickscroll.StickyScrollActivity;
import demo.hpg.org.pauldemo.view.swipelayout.SwipeLayoutActivity;
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

    private Toolbar toolbar;
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
    private Button recycleButton;

    private Button rxjavaButton;

    private Button swipeButton;

    private Button pullTozoomButton;

    private Button propetyButton;

    private Button overtimeButton;

    private Button mediaButton;

    private Button videoButton;

    private Button autoButton;

    private Button optimizeButton;

    private Button behaviorButton;

    private Button loadmoreButton;

    private Button listButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolbar();
        showInfo();
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_launcher);
        toolbar.setTitle("PaulDemo");
        toolbar.setSubtitle("demo合集，方便查阅");
        toolbar.setNavigationIcon(R.drawable.close);
        setSupportActionBar(toolbar);
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

        findViewAndOnclick(recycleButton,R.id.recycle);

        findViewAndOnclick(rxjavaButton,R.id.rxjava);

        findViewAndOnclick(swipeButton,R.id.swipe);

        findViewAndOnclick(pullTozoomButton,R.id.pulltozoom);

        findViewAndOnclick(propetyButton,R.id.propety);

        findViewAndOnclick(overtimeButton,R.id.overtime);

        findViewAndOnclick(mediaButton,R.id.media);

        findViewAndOnclick(videoButton,R.id.video);

        findViewAndOnclick(autoButton,R.id.aotomove);

        findViewAndOnclick(optimizeButton,R.id.optimize);

        findViewAndOnclick(behaviorButton,R.id.behavior);

        findViewAndOnclick(loadmoreButton,R.id.loadmore);

        findViewAndOnclick(listButton,R.id.list);
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
            case R.id.recycle:
                openActivity(RecycleActivity.class);
                break;
            case R.id.rxjava:
                openActivity(RxJavaActivity.class);
                break;
            case R.id.swipe:
                openActivity(SwipeLayoutActivity.class);
                break;
            case R.id.pulltozoom:
                openActivity(PullToZoomActivity.class);
                break;
            case R.id.propety:
                openActivity(PropertyActivity.class);
                break;
            case R.id.overtime:
                openActivity(OverTimeActivity.class);
                break;
            case R.id.media:
                openActivity(AudioActivity.class);
                break;
            case R.id.video:
                openActivity(VideoActivity.class);
                break;
            case R.id.aotomove:
                openActivity(AutoMoveActivity.class);
                break;
            case R.id.optimize:
                openActivity(OptimizeActivity.class);
                break;
            case R.id.behavior:
                openActivity(BehaviorActivity.class);
                break;
            case R.id.loadmore:
                openActivity(LoadmoreActivity.class);
                break;
            case R.id.list:
                ListTestActivity.launch(MainActivity.this);
                break;
        }
    }

    private void findViewAndOnclick(Button button,int resourceId){
        button = fvById(resourceId);
        button.setOnClickListener(this);
    }

    private void showInfo(){
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        StringBuilder sb = new StringBuilder();
        sb.append("\nDeviceId(IMEI) = " + tm.getDeviceId());
        sb.append("\nDeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion());
        sb.append("\nLine1Number = " + tm.getLine1Number());
        sb.append("\nNetworkCountryIso = " + tm.getNetworkCountryIso());
        sb.append("\nNetworkOperator = " + tm.getNetworkOperator());
        sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());
        sb.append("\nNetworkType = " + tm.getNetworkType());
        sb.append("\nPhoneType = " + tm.getPhoneType());
        sb.append("\nSimCountryIso = " + tm.getSimCountryIso());
        sb.append("\nSimOperator = " + tm.getSimOperator());
        sb.append("\nSimOperatorName = " + tm.getSimOperatorName());
        sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
        sb.append("\nSimState = " + tm.getSimState());
        sb.append("\nSubscriberId(IMSI) = " + tm.getSubscriberId());
        sb.append("\nVoiceMailNumber = " + tm.getVoiceMailNumber());
        Log.e("info", sb.toString());
        Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings||id == R.id.action_edit || id == R.id.action_share) {
//            return true;
//        }
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                break;
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "action_setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(MainActivity.this, "action_location", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_edit:
                Toast.makeText(MainActivity.this, "action_search", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}