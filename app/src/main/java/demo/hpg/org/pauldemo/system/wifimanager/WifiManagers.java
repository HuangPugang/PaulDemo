package demo.hpg.org.pauldemo.system.wifimanager;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Author huarizhong
 * Date 2015/3/18
 * Time 11:09
 */
public class WifiManagers {

    private WifiManager mWifiManager;//wifimanager对象

    private WifiInfo mWifiInfo;//wifi对象

    private List<ScanResult> mWifiList;//扫描出的wifi列表

    private List<WifiConfiguration> mWifiConfigurationList;//网络链接列表

    WifiManager.WifiLock mWifiLock;

    public WifiManagers(Context context){
        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        //wifiinfo对象
        mWifiInfo = mWifiManager.getConnectionInfo();

    }

    //打开wifi
    public void openWifi(){
        if (!mWifiManager.isWifiEnabled()){
            mWifiManager.setWifiEnabled(true);
        }
    }
    //关闭wifi
    public void closeWifi(){
        if (mWifiManager.isWifiEnabled()){
            mWifiManager.setWifiEnabled(false);
        }
    }

    //检查wifi状态
    public int checkState(){
        return mWifiManager.getWifiState();
    }
    //锁定wifi
    public void acquireWifiLock(){
        mWifiLock.acquire();
    }
    //解锁wifi
    public void releaseWifiLock(){
        if (mWifiLock.isHeld()){
            mWifiLock.acquire();
        }
    }
    //创建wifilock
    public void createWifiLock(){
        mWifiLock = mWifiManager.createWifiLock("test");
    }
    //得到配置好的网络
    public List<WifiConfiguration> getWifiConfigurationList(){
        return mWifiConfigurationList;
    }

    //指定配置好的网络进行链接
    public void connectionConfiguration(int index){
        if (index> mWifiConfigurationList.size()){
            return ;
        }
        //链接配置好指定id的网络
        mWifiManager.enableNetwork(mWifiConfigurationList.get(index).networkId, true);
    }

    public void startScan(){
        mWifiManager.startScan();
        //得到结果
        mWifiList = mWifiManager.getScanResults();
        //得到配置好的网络链接
        mWifiConfigurationList = mWifiManager.getConfiguredNetworks();
    }

    //得到网络列表
    public List<ScanResult> getWifiList(){
        return mWifiList;
    }

    //查看扫描结果
    public StringBuffer lookUpScan(){
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<mWifiList.size();i++){
            sb.append("Index_"+new Integer(i+1).toString()+":");
            sb.append(mWifiList.get(i).toString());
        }
        return sb;
    }

    public String getMacAddress(){
        return (mWifiInfo==null)?"null":mWifiInfo.getMacAddress();
    }

    public String getBSSID(){
        return  (mWifiInfo==null)?"null":mWifiInfo.getBSSID();
    }

    public int getIPAddress(){
        return  (mWifiInfo==null)?0:mWifiInfo.getIpAddress();
    }

    //得到链接的id
    public int getNetWorkId(){
        return  (mWifiInfo==null)?0:mWifiInfo.getNetworkId();
    }

    //得到wifi所有信息
    public String getWifiInfo(){
        return (mWifiInfo==null)?"null":mWifiInfo.toString();
    }

    //添加网络链接
    public void addNetWork(WifiConfiguration confi){
        int wcgId = mWifiManager.addNetwork(confi);
        mWifiManager.enableNetwork(wcgId,true);
    }
    //断开指定id的网络
    public void disConnectionWifi(int netId){
        mWifiManager.disableNetwork(netId);
        mWifiManager.disconnect();
    }

    public void createWifi(String password){

        boolean bRet1 = mWifiManager.setWifiEnabled(true);

        if (mWifiManager.startScan()) //扫描可用的无线网络
        {
            List<ScanResult> scanResultList = mWifiManager.getScanResults();

            for (int i = 0; i < scanResultList.size(); i++)
            {
                ScanResult scanRet = scanResultList.get(i);

                if (scanRet.SSID.equalsIgnoreCase("TEST-2")) //找到 TEST
                {

                    WifiConfiguration config = new WifiConfiguration();

                    config.SSID = "\"" + scanRet.SSID + "\"";

                    config.preSharedKey = "\""+password+"\""; //指定密码

                    config.hiddenSSID = true;

                    config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);

                    config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);

                    config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);

                    config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);

                    config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);

                    config.status = WifiConfiguration.Status.ENABLED;

                    int netID = mWifiManager.addNetwork(config);

                    boolean bRet = mWifiManager.enableNetwork(netID, true);

                }

            }

        }
    }
}
