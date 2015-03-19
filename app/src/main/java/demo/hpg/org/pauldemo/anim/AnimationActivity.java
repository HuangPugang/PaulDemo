package demo.hpg.org.pauldemo.anim;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import demo.hpg.org.pauldemo.MainActivity;
import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/19
 * Time 11:26
 */
public class AnimationActivity extends Activity {
    private Button change;
    LinearLayout linearLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        change = (Button) findViewById(R.id.start);
        linearLayout = (LinearLayout) findViewById(R.id.layout);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationView animationView=new AnimationView(AnimationActivity.this);
                linearLayout.addView(animationView);
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            this.finish();
            overridePendingTransition(R.anim.hold,R.anim.bottom_out);
        }
        return super.onKeyDown(keyCode, event);
    }

    public class AnimationView extends View{
        public AnimationView(Context context) {
            super(context);
            ObjectAnimator objectAnimator=
                    (ObjectAnimator) AnimatorInflater.loadAnimator(AnimationActivity.this, R.animator.color_animation);
            objectAnimator.setEvaluator(new ArgbEvaluator());
            objectAnimator.setTarget(this);
            objectAnimator.start();
        }

    }
}
