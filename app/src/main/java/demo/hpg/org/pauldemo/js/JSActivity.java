package demo.hpg.org.pauldemo.js;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/30 16:49
 * PackageName demo.hpg.org.pauldemo.js
 */
public class JSActivity extends Activity {
    private WebView mWebView;
    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //无参调用
                mWebView.loadUrl("javascript:javacalljs()");
                //传递参数调用
                mWebView.loadUrl("javascript:javacalljswithargs("+"'hello world'"+")");
            }
        });
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/wst.html");
        mWebView.addJavascriptInterface(this,"wst");
    }

    @JavascriptInterface
    public void startFunction(){
        Toast.makeText(this,"js 调用了java函数1--无参数",Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public void startFunction(String str){
        Toast.makeText(this,"js 调用了java函数2--有参数"+ str,Toast.LENGTH_SHORT).show();
    }
}
