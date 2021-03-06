package demo.hpg.org.pauldemo.rxjava;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Paul on 15/11/26.
 */
public class RxJavaActivity extends BaseActivity {


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
        // 观察事件发生
        Observable.OnSubscribe mObservableAction = new Observable.OnSubscribe<String>() {
            @Override public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello test"); // 发送事件
                subscriber.onCompleted(); // 完成事件
            }
        };
        Observable<String> observable = Observable.create(mObservableAction);
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(setTextSubscriber);
        observable.subscribe(toastSubscriber);
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
            Toast.makeText(RxJavaActivity.this,s,Toast.LENGTH_SHORT).show();
        }
    };


}
