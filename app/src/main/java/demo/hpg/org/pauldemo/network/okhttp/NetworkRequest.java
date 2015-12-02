package demo.hpg.org.pauldemo.network.okhttp;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Paul on 15/11/24.
 */
public class NetworkRequest {
    private ConcurrentHashMap<String, String> mParams;
    private static NetworkRequest mNetworkRequest;
    private ResponseCallback mCallback;
    private OkHttpClient mOkHttpClient;

    private NetworkRequest() {
        mOkHttpClient = new OkHttpClient();
    }

    public static NetworkRequest getInstance() {
        if (mNetworkRequest == null) {
            synchronized (NetworkRequest.class) {
                mNetworkRequest = new NetworkRequest();
            }
        }
        return mNetworkRequest;
    }


    public void dispatchRequest(Type type, ResponseCallback callback, int tag, String url, ConcurrentHashMap<String, String> params) {
        try {
            sendRequest(type, callback, tag, url, params);
        } catch (Exception e) {
            e.printStackTrace();
            mCallback.onFailed(tag);
        }

    }

    private void sendRequest(Type type, ResponseCallback callback, int tag, String url, ConcurrentHashMap<String, String> params) throws Exception {
        this.mCallback = callback;
        if (type == Type.GET) {
            doGetRequest(url, params);
        } else if (type == Type.POST) {
            doPostRequest(url, params);
        }
    }


    public enum Type {
        GET, POST
    }


    public void doGetRequest(String url, ConcurrentHashMap<String, String> params) throws Exception {

        String requestUrl = null;
        // 如果是GET请求，则请求参数在URL中
        if (params != null && !params.isEmpty()) {
            String param = urlEncode(params);
            requestUrl = url + "?" + param;
        } else {
            requestUrl = url;
        }

        Request request = new Request.Builder()
                .url(requestUrl)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.e("HPG", response.body().string());

            }
        });

    }

    public void doPostRequest(String url, ConcurrentHashMap<String, String> params) {
        FormEncodingBuilder builder = new FormEncodingBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.e("HPG", response.body().string());
            }
        });

    }

    private String urlEncode(ConcurrentHashMap<String, String> params)
            throws UnsupportedEncodingException {
        StringBuffer sb2 = new StringBuffer();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                sb2.append(entry.getKey());
                sb2.append("=");
                sb2.append(URLEncoder.encode(entry.getValue(), "utf-8").toString());
                sb2.append("&");
            }
        }
        String s = "" ;
        if (sb2.length()!=0) {
            s = sb2.substring(0, sb2.length() - 1);
        }
        return s;
    }


}
