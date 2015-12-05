package demo.hpg.org.pauldemo.view.swipelayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;

/**
 * Created by Paul on 15/11/30.
 */
public class SwipeLayoutActivity extends BaseActivity {
    private SwipeLayout sample3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipelayout);

        sample3 = (SwipeLayout) findViewById(R.id.sample3);
        sample3.addDrag(SwipeLayout.DragEdge.Top, sample3.findViewWithTag("Bottom"));
        sample3.addRevealListener(R.id.bottom_wrapper_child1, new SwipeLayout.OnRevealListener() {
            @Override
            public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {
                View star = child.findViewById(R.id.star);
                float d = child.getHeight() / 2 - star.getHeight() / 2;
//                ViewHelper.setTranslationY(star, d * fraction);
//                ViewHelper.setScaleX(star, fraction + 0.6f);
//                ViewHelper.setScaleY(star, fraction + 0.6f);
                int c = (Integer) evaluate(fraction, Color.parseColor("#dddddd"), Color.parseColor("#4C535B"));
                child.setBackgroundColor(c);
            }
        });
        sample3.findViewById(R.id.bottom_wrapper_child1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Yo!", Toast.LENGTH_SHORT).show();
            }
        });
        sample3.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Click on surface", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*
Color transition method.
 */
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
                (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
                (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
                (int) ((startB + (int) (fraction * (endB - startB))));
    }
}
