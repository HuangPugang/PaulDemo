package demo.hpg.org.pauldemo.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import demo.hpg.org.pauldemo.R;

/**
 * Created by pau on 15/3/19.
 */
public class MyWebview extends Activity {
    private WebView webView;
    private String url = "http://www.w3school.com.cn/js/js_intro.asp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl(url);
    }
}
