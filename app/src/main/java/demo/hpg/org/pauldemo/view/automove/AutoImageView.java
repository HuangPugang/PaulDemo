package demo.hpg.org.pauldemo.view.automove;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


import android.graphics.Rect;
import android.widget.ImageView;

public class AutoImageView extends ImageView {
    //按下Y轴坐标
    private int mDownY;

    private int mDownX;

    private int mLastX;
    private int mLastY;

    private int mCurrentX;
    private int mCurrentY;
    private Rect normal = new Rect();


    public AutoImageView(Context context) {
        super(context);
    }

    public AutoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                mDownY = mLastY = (int) ev.getRawY();
                mDownX =  mLastX = (int) ev.getRawX();
                Log.e("HPG", "getRawX=" + ev.getRawX() + "\ngetRawY=" + ev.getRawY() + "\ngetTop=" + this.getTop() + "\ngetRight=" + this.getRight() + "\ngetBottom=" + getBottom() + "\ngetLeft=" + getLeft());
                break;
            case MotionEvent.ACTION_UP:
//                AnimatorSet set = new AnimatorSet();
//                set.playTogether(
//                        ObjectAnimator.ofFloat(this, "translationY", ev.getRawY(),mDownY),
//                        ObjectAnimator.ofFloat(this, "translationX", ev.getRawX(), mDownX)
//                );
//                set.setDuration(400).start();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentX = (int) ev.getRawX();
                mCurrentY = (int) ev.getRawY();
                int deltX = mCurrentX - mLastX;
                int deltY = mCurrentY - mLastY;
                mLastX = (int) ev.getRawX();
                mLastY = (int) ev.getRawY();
                Log.e("HPG", "getRawX=" + ev.getRawX() + "\ngetRawY=" + ev.getRawY() + "\ngetTop=" + this.getTop() + "\ngetRight=" + this.getRight() + "\ngetBottom=" + getBottom() + "\ngetLeft=" + getLeft());
                this.layout((int) (getLeft() + deltX), (int) (getTop() + deltY), (int) (getRight() + deltX), (int) (getBottom() + deltY));
                break;
            default:
                break;
        }
        return true;
    }


}
