package demo.hpg.org.pauldemo.network.upload;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Author huarizhong
 * Date 2015/3/24 16:23
 * PackageName demo.hpg.org.pauldemo.upload
 */
public class NetWork implements Runnable {
    private static final String TAG="uploadFile";
    private static final int TIME_OUT=5*5000;
    private static final String CHARSET="utf-8";
    public static final String SUCCESS = "1";
    public static final String FAILURE = "0";


    @Override
    public void run() {
        try {
            URL url = new URL(UploadActivity.REQUEST_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(TIME_OUT);
            connection.setReadTimeout(TIME_OUT);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(reader);
            String line=null;
            StringBuffer sb= new StringBuffer();
            sb.setLength(0);
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
            Log.e(TAG,sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
