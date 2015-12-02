package demo.hpg.org.pauldemo.view.moveview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author huarizhong
 * Date 2015/3/11
 * Time 16:31
 */
public class ViewTest extends View {
    public ViewTest(Context context) {
        super(context);
    }

    public ViewTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("TAG",event.getX()+"");

                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TAG",event.getX()+"");

                break;
            case MotionEvent.ACTION_UP:

                Log.e("TAG",event.getX()+"");
                break;
        }
        return true;
    }


}
