package demo.hpg.org.pauldemo.view.requestlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Paul on 15/11/21.
 */
public class ElasticScrollView extends ScrollView implements View.OnScrollChangeListener{
    private float mDownY;
    private float mCurrentY;

    private float moveY;
    private View mFirstChild;

    public ElasticScrollView(Context context) {
        super(context);
    }

    public ElasticScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ElasticScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            mFirstChild = getChildAt(0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("HPG","getHeight="+getHeight()+",getScrollY="+getScrollY()
                        +",computeVerticalScrollRange="+computeVerticalScrollRange()
                        +"mFirstChild.getMeasuredHeight()="+mFirstChild.getMeasuredHeight());
//                if (getScrollY() + getHeight() >= computeVerticalScrollRange()) {//滚动到底部
//                    final float preY = mDownY == 0 ? ev.getY() : mDownY;
//                    float currentY = ev.getY();
//                    int deltaY = (int) (preY - currentY);
//                    ViewHelper.setTranslationY(this, deltaY);
//                }
                break;

        }

        return super.onTouchEvent(ev);
    }


    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

    }
}
