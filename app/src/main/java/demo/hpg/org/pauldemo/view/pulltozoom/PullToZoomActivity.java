package demo.hpg.org.pauldemo.view.pulltozoom;

import android.os.Bundle;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;
import demo.hpg.org.pauldemo.view.tanxingscrollview.PullToZoomScrollView;

/**
 * Created by Paul on 15/11/30.
 */
public class PullToZoomActivity extends BaseActivity implements PullToZoomScrollView.OnMyScrollListener{
    private PullToZoomScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltozoom);
        mScrollView = fvById(R.id.scrollview_film);
        mScrollView.setOnMyScrollListener(this);
    }

    @Override
    public void onScroll(float alpha, boolean isShow) {

    }
}
