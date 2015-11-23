package demo.hpg.org.pauldemo.view.requestlayout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import demo.hpg.org.pauldemo.R;

/**
 * Created by Paul on 15/11/20.
 */
public class RequestLayoutActvity extends Activity {
    private RequestView view ;
    private Button big;
    private Button small;
    private int height = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestlayout);
        view = (RequestView) findViewById(R.id.request_layout);
        big = (Button) findViewById(R.id.big);
        big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height = height +10;
                view.requestLayout(height);
            }
        });
        small = (Button) findViewById(R.id.small);
        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height = height -10;
                view.requestLayout(height);
            }
        });
        test();
    }

    private void test() {
        ObjectAnimator oa=ObjectAnimator.ofFloat(big, "alpha", 0f, 1f);
        oa.setDuration(3000);
        oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //Log.i("update", ((Float) animation.getAnimatedValue()).toString());
            }
        });
        oa.start();
    }
}
