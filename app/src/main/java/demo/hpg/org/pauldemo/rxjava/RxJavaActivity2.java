package demo.hpg.org.pauldemo.rxjava;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;
import rx.Subscriber;

/**
 * Created by Paul on 15/11/26.
 */
public class RxJavaActivity2 extends BaseActivity {


    private String[] names = {"huang", "lin", "zhu"};
    private int drawableRes = R.drawable.ic_launcher;
    private ImageView imageView;
    private TextView mTvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        imageView = fvById(R.id.iv_test);
        mTvTest = fvById(R.id.tv_test);


    }

    Subscriber<String> setTextSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            mTvTest.setText(s);
        }
    };

    Subscriber<String> toastSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Toast.makeText(RxJavaActivity2.this,s,Toast.LENGTH_SHORT).show();
        }
    };


}
