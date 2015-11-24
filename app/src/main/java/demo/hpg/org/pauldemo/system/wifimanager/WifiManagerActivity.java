package demo.hpg.org.pauldemo.system.wifimanager;

import android.app.Activity;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/18
 * Time 11:06
 */
public class WifiManagerActivity extends Activity {
    public static final String WIFI_PASSWORD="wifi_password";
    private TextView allNetWork;
    private Button scan;
    private Button start;
    private Button stop;
    private Button check;
    private Button getIp;
    private Button connect;
    private ListView listView ;
    private WifiManagers managers;
    // 扫描结果列表
    private List<ScanResult> list;
    private ScanResult mScanResult;
    private StringBuffer sb=new StringBuffer();
    private String[] wifiArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_manager);
        managers = new WifiManagers(this);
        init();
    }
    public void init(){
        allNetWork = (TextView) findViewById(R.id.allNetWork);
        scan = (Button) findViewById(R.id.scan);
        start = (Button) findViewById(R.id.change);
        stop = (Button) findViewById(R.id.stop);
        check = (Button) findViewById(R.id.check);
        getIp = (Button) findViewById(R.id.get_ip);
        connect = (Button) findViewById(R.id.connect);
        listView = (ListView) findViewById(R.id.wifi_list);
        scan.setOnClickListener(new MyListener());
        start.setOnClickListener(new MyListener());
        stop.setOnClickListener(new MyListener());
        check.setOnClickListener(new MyListener());
        getIp.setOnClickListener(new MyListener());
        connect.setOnClickListener(new MyListener());
    }
    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.scan://扫描网络
                    getAllNetWorkList();
                    listView.setAdapter(new ArrayAdapter<String>(WifiManagerActivity.this,
                            android.R.layout.simple_list_item_1, wifiArr));
                    break;
                case R.id.change://打开Wifi
                    managers.openWifi();
                    Toast.makeText(WifiManagerActivity.this, "当前wifi状态为：" + managers.checkState(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.stop://关闭Wifi
                    managers.closeWifi();
                    Toast.makeText(WifiManagerActivity.this, "当前wifi状态为："+managers.checkState(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.check://Wifi状态
                    Toast.makeText(WifiManagerActivity.this, "当前wifi状态为："+managers.checkState(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.get_ip://ip
                    Toast.makeText(WifiManagerActivity.this,"IP地址为："+managers.getIPAddress(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.connect:
                    managers.createWifi("abced66388");
                    break;
                default:
                    break;
            }
        }

    }

    //获得wifi列表
    public void getAllNetWorkList(){
        // 每次点击扫描之前清空上一次的扫描结果
//        if(sb!=null){
//            sb=new StringBuffer();
//        }
        //开始扫描网络
        managers.startScan();
        list=managers.getWifiList();
//        if(list!=null){
//            for(int i=0;i<list.size();i++){
//                //得到扫描结果
//                mScanResult=list.get(i);
//                sb=sb.append(mScanResult.BSSID+"\n").append(mScanResult.SSID + "\n")
//                        .append(mScanResult.capabilities + "\n").append(mScanResult.frequency + "\n")
//                        .append(mScanResult.level + "\n\n");
//            }
//            allNetWork.setText("扫描到的wifi网络：\n"+sb.toString());
//        }
        wifiArr = new String[list.size()];
        for (int i=0;i<list.size();i++){
            mScanResult=null;
            mScanResult=list.get(i);
            wifiArr[i]=mScanResult.SSID;
        }
    }
}
