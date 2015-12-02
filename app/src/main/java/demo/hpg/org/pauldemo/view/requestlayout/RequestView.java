package demo.hpg.org.pauldemo.view.requestlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Paul on 15/11/20.
 */
public class RequestView extends View {
    public RequestView(Context context) {
        super(context);
    }

    public RequestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RequestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void requestLayout(int height) {
        this.getLayoutParams().height = height;
        this.requestLayout();
    }
}
