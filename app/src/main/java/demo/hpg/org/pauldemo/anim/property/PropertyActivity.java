package demo.hpg.org.pauldemo.anim.property;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;
import demo.hpg.org.pauldemo.view.automove.AutoMoveView;

/**
 * Created by Paul on 15/12/2.
 */
public class PropertyActivity extends BaseActivity implements View.OnClickListener {
    private Button translateButton;
    private Button rotateXButton;
    private Button rotateYButton;
    private Button rotateZButton;
    private Button rotateButton;
    private Button scaleButton;
    private AutoMoveView scrollToButton;
    private Button scrollByButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        translateButton = (Button) findViewById(R.id.trans);
        translateButton.setOnClickListener(this);
        rotateXButton = (Button) findViewById(R.id.rotateX);
        rotateXButton.setOnClickListener(this);
        rotateYButton = (Button) findViewById(R.id.rotateY);
        rotateYButton.setOnClickListener(this);
        rotateZButton = (Button) findViewById(R.id.rotateZ);
        rotateZButton.setOnClickListener(this);
        rotateButton = (Button) findViewById(R.id.rotate);
        rotateButton.setOnClickListener(this);
        scaleButton = (Button) findViewById(R.id.scale);
        scaleButton.setOnClickListener(this);

        scrollToButton = (AutoMoveView) findViewById(R.id.scrollto);
        scrollToButton.setOnClickListener(this);

        scrollByButton = (Button) findViewById(R.id.scrollby);
        scrollByButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.trans) {
            translate();
        } else if (viewId == R.id.rotateX) {
            rotateX();
        } else if (viewId == R.id.rotateY) {
            rotateZ();
            rotateY();
        } else if (viewId == R.id.rotateZ) {
            rotateZ();
        } else if (viewId == R.id.rotate) {
            rotate();
        } else if (viewId == R.id.scale) {
            scale();
        } else if (viewId == R.id.scrollto) {
        } else if (viewId == R.id.scrollby) {
        }
    }

    private void translate() {
        ObjectAnimator.ofFloat(translateButton, "translationY", 0, 200).setDuration(400).start();
    }

    private void rotateX() {
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(
                ObjectAnimator.ofFloat(rotateXButton, "rotationX", 0, 90).setDuration(200),
                ObjectAnimator.ofFloat(rotateXButton, "rotationX", -90, 0).setDuration(200)
        );
        set.setDuration(400).start();
    }

    private void rotateY() {
        AnimatorSet set = new AnimatorSet();
        rotateYButton.setPivotY(0);
        set.playSequentially(
                ObjectAnimator.ofFloat(rotateYButton, "rotationY", 0, 90).setDuration(200),
                ObjectAnimator.ofFloat(rotateYButton, "rotationY", -90, 0).setDuration(200)
        );
        set.setDuration(400).start();
    }

    private void rotateZ() {
        rotateZButton.setVisibility(View.VISIBLE);
        rotateZButton.setPivotY(0);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(
                ObjectAnimator.ofFloat(rotateZButton, "rotationY", -90, 0).setDuration(400)
        );
        set.setDuration(400).start();
    }

    private void rotate() {
        rotateButton.setPivotX(rotateButton.getWidth());
        rotateButton.setPivotY(rotateButton.getHeight() / 2);
        ObjectAnimator.ofFloat(rotateButton, "rotation", 0, -90).setDuration(400).start();

    }

    private void scale() {
        scaleButton.setPivotX(0);
        scaleButton.setPivotY(rotateButton.getHeight() / 2);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(scaleButton, "scaleX", 1, 1.5f).setDuration(400),
                ObjectAnimator.ofFloat(scaleButton, "scaleY", 1, 1.5f).setDuration(400)
        );
        set.setDuration(400).start();
    }


}
