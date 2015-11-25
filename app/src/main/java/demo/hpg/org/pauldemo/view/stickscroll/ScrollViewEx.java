package demo.hpg.org.pauldemo.view.stickscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import demo.hpg.org.pauldemo.R;

/**
 * Created by Paul on 15/11/25.
 */
public class ScrollViewEx extends ScrollView {
    private LinearLayout mFirstChild;
    private boolean isFirst = true;
    private LinearLayout mFloatLayout;
    private IOnScrollListener mOnScrollListener;
    public ScrollViewEx(Context context) {
        super(context);
        init();
    }

    public ScrollViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnScrollListener(IOnScrollListener iOnScrollListener){
        this.mOnScrollListener = iOnScrollListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isFirst) {
            mFirstChild = (LinearLayout) getChildAt(0);
            isFirst = false;
        }
    }

    private void init() {
        Log.e("HPG", "init");

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.e("HPG", "onScrollChanged" + t);
        mOnScrollListener.onScroll(t);
    }

    public interface IOnScrollListener {
        public void onScroll(int t);
    }
}
