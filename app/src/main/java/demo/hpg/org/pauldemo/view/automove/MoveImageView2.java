package demo.hpg.org.pauldemo.view.automove;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by joybar on 15/12/3.
 */
public class MoveImageView2 extends ImageView {

    private static  String  TAG = "MoveImageView2";
    private int screenW;
    private int screenH;
    private int statusH;
    private int navigationH;
    private int clickX;
    private int clickY;


    private int topTitleHeight;
    public MoveImageView2(Context context) {
        super(context);

    }

    public MoveImageView2(Context context, AttributeSet attrs) {
        super(context, attrs, 0);

    }

    public MoveImageView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }


    public void setStatusH(int statusH){
        this.statusH = statusH;
    }

    public void setScreenH(int screenH){
        this.screenH = screenH;
    }
    public void setScreenW(int screenW){
        this.screenW = screenW;
    }
    public void setavigation(int navigationH){
        this.navigationH = navigationH;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int[] locations = new int[2];
                this.getLocationInWindow(locations);
                topTitleHeight = locations[1];
//                L.i(TAG,"X="+locations[0]+"");
//                L.i(TAG,"Y="+topTitleHeight+"");
                clickX = (int)event.getX();
                clickY = (int)event.getY();
                Log.i(TAG, "clickX=" + event.getX());
                Log.i(TAG, "clickY=" + event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                moveViewByLayout(this, (int) event.getRawX(),
                        (int) event.getRawY());

                Log.i(TAG,"MOVE_RX="+event.getRawX());
                Log.i(TAG, "MOVE_RY=" + event.getRawY());

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }


    //û��
    /**  
     * @param view
     * @param rawX
     * @param rawY
     * @param scale
     */
    private void moveViewByLayout(View view, int rawX, int rawY, int scale) {
        int left = rawX - this.getWidth() / 2;
        int top = rawY - topTitleHeight - this.getHeight() / 2;
        int width = left + (int) (view.getWidth() + scale);
        int height = top + (int) (view.getHeight() + scale);
        view.layout(left, top, width, height);
    }

    /**

     *
     * @param view
     * @param rawX
     * @param rawY
     */
    private void moveViewByLayout(View view, int rawX, int rawY) {


        int left = rawX -clickX;
        if(left<0){
            left =0;
        }
        int top = rawY- clickY - statusH;
        Log.i(TAG, "statusH=" +statusH);
        Log.i(TAG, "this.getHeight()=" +this.getHeight());
        if(top<0){
            top =0;
        }
        int Right = left + view.getWidth();
        if(screenW!=0&&Right>screenW){
          //  Right =screenW;
            Log.i(TAG, "AAAAARight=" +Right);
            Log.i(TAG, "AAAAAAAscreenW=" +screenW);
            return;
        }
       int Bottom = top + view.getHeight();

       if(screenH!=0&&Bottom>screenH+statusH+navigationH+view.getHeight()){

            return;
        }


        view.layout(left, top, Right, Bottom);
    }

}