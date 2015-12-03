package demo.hpg.org.pauldemo.overtime;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Paul on 15/12/2.
 */
public class OverTimeActivity extends BaseActivity {
    private TextView overText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overtime);
        overText = (TextView) findViewById(R.id.over);
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext(1);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onNext(Object drawable) {
                        Log.e("HPG", drawable.toString() + "=drawable");
                    }

                    @Override
                    public void onCompleted() {
                        Log.e("HPG","completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(OverTimeActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
