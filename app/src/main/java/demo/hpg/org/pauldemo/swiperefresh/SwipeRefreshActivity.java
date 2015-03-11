package demo.hpg.org.pauldemo.swiperefresh;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/11
 * Time 18:00
 */
public class SwipeRefreshActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String[] TITLES =
            {
                    "Henry IV (1)",
                    "Henry V",
                    "Henry VIII",
                    "Richard II",
                    "Richard III",
                    "Merchant of Venice",
                    "Othello",
                    "King Lear",
                    "Henry IV (1)",
                    "Henry V",
                    "Henry VIII",
                    "Richard II",
                    "Richard III",
                    "Merchant of Venice",
                    "Othello",
                    "King Lear",
                    "Henry IV (1)",
                    "Henry V",
                    "Henry VIII",
                    "Richard II",
                    "Richard III",
                    "Merchant of Venice",
                    "Othello",
                    "King Lear",
                    "Henry IV (1)",
                    "Henry V",
                    "Henry VIII",
                    "Richard II",
                    "Richard III",
                    "Merchant of Venice",
                    "Othello",
                    "King Lear"
            };
    // Try a SUPER quick refresh to make sure we don't get extra refreshes
    // while the user's finger is still down.
    private static final boolean SUPER_QUICK_REFRESH = false;
    private View mContent;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private ListView mList;
    private Handler mHandler = new Handler();
    private final Runnable mRefreshDone = new Runnable() {

        @Override
        public void run() {
            mSwipeRefreshWidget.setRefreshing(false);
        }

    };
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_swiperefresh);
        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mList = (ListView) findViewById(R.id.content);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, TITLES);
        mList.setAdapter(arrayAdapter);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        refresh();
    }



    private void refresh() {
        mHandler.removeCallbacks(mRefreshDone);
        mHandler.postDelayed(mRefreshDone, 1000);
    }
}
