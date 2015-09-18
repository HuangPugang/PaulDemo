//package demo.hpg.org.pauldemo.wifimanager;
//
//import android.app.Activity;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.View;
//import android.widget.Button;
//
//
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.net.SocketException;
//import java.util.Enumeration;
//
//import demo.hpg.org.pauldemo.R;
//
///**
// * Author huarizhong
// * Date 2015/3/18
// * Time 14:20
// */
//public class WifiApActivity extends Activity {
//    public static final String TAG = "MainActivity";
//
//    private Button mBtn1, mBtn2;
//
//    private WifiAdmin mWifiAdmin;
//
//    private Context mContext = null;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mContext = this;
//
//        setContentView(R.layout.activity_wifi_ap);
//
//        mBtn1 = (Button)findViewById(R.id.button1);
//        mBtn2 = (Button)findViewById(R.id.button2);
//
//        mBtn1.setText("点击连接Wifi");
//        mBtn1.setText(getLocalHostIp());
//        mBtn2.setText("点击创建Wifi热点");
//        mBtn1.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//
//                mWifiAdmin = new WifiAdmin(mContext) {
//
//                    @Override
//                    public void myUnregisterReceiver(BroadcastReceiver receiver) {
//                        // TODO Auto-generated method stub
//                        WifiApActivity.this.unregisterReceiver(receiver);
//                    }
//
//                    @Override
//                    public Intent myRegisterReceiver(BroadcastReceiver receiver,
//                                                     IntentFilter filter) {
//                        // TODO Auto-generated method stub
//                        WifiApActivity.this.registerReceiver(receiver, filter);
//                        return null;
//                    }
//
//                    @Override
//                    public void onNotifyWifiConnected() {
//                        // TODO Auto-generated method stub
//                        Log.v(TAG, "have connected success!");
//                        Log.v(TAG, "###############################");
//                    }
//
//                    @Override
//                    public void onNotifyWifiConnectFailed() {
//                        // TODO Auto-generated method stub
//                        Log.v(TAG, "have connected failed!");
//                        Log.v(TAG, "###############################");
//                    }
//                };
//                mWifiAdmin.openWifi();
//                mWifiAdmin.addNetwork(mWifiAdmin.createWifiInfo("YOU_WIFI", "MM123456", WifiAdmin.TYPE_WPA));
//
//            }
//        });
//
//        mBtn2.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//
//                WifiApAdmin wifiAp = new WifiApAdmin(mContext);
//                wifiAp.startWifiAp("\"HotSpot\"", "hhhhhh123");
//            }
//        });
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        Log.d("Rssi", "Registered");
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//
//        Log.d("Rssi", "Unregistered");
//    }
//
//
//    public static String getLocalHostIp() {
//        String ipaddress = "";
//        try {
//            Enumeration<NetworkInterface> en = NetworkInterface
//                    .getNetworkInterfaces();
//            // 遍历所用的网络接口
//            while (en.hasMoreElements()) {
//                NetworkInterface nif = en.nextElement();// 得到每一个网络接口绑定的所有ip
//                Enumeration<InetAddress> inet = nif.getInetAddresses();
//                // 遍历每一个接口绑定的所有ip
//                while (inet.hasMoreElements()) {
//                    InetAddress ip = inet.nextElement();
//                    if (!ip.isLoopbackAddress()
//                            && InetAddressUtils.isIPv4Address(ip
//                            .getHostAddress())) {
//                        return ipaddress = ip.getHostAddress();
//                    }
//                }
//
//            }
//        } catch (SocketException e) {
//            System.out.print("获取IP失败");
//            e.printStackTrace();
//        }
//        return ipaddress;
//
//    }
//}
