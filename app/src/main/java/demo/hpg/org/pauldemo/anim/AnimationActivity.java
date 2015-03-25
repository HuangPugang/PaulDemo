package demo.hpg.org.pauldemo.anim;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/19
 * Time 11:26
 */
public class AnimationActivity extends Activity {
    private Button changeBG;
    private RelativeLayout relativeLayout;
    private Button showTips;
    private LayoutInflater inflater ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        inflater = LayoutInflater.from(this);
        changeBG = (Button) findViewById(R.id.change);
        showTips = (Button) findViewById(R.id.show_tip);
        relativeLayout = (RelativeLayout) findViewById(R.id.layout);


        changeBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationView animationView = new AnimationView(AnimationActivity.this);
                relativeLayout.addView(animationView);
            }
        });

        showTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListRefreshPopResult();
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

    /**
     * 属性动画
     */
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

    /**
     * 刷新提示
     */
    protected void showListRefreshPopResult() {

        final View tipView  = inflater.inflate(R.layout.tip,null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        relativeLayout.addView(tipView, params);
        final Animation aniin = new AnimationUtils().loadAnimation(this,
                R.anim.tip_push_in);
        final Animation aniout = new AnimationUtils().loadAnimation(this,
                R.anim.tip_push_out);
        aniout.setDuration(2000);

        tipView.startAnimation(aniin);
        aniin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub

                tipView.startAnimation(aniout);
            }
        });
        aniout.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                relativeLayout.removeView(tipView);
            }
        });
    }
}
