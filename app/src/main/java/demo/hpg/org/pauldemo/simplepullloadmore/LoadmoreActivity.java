package demo.hpg.org.pauldemo.simplepullloadmore;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;

/**
 * Created by paul on 15/12/15.
 */
public class LoadmoreActivity extends BaseActivity {
    private static final String[] strs = new String[]{
            "first", "second", "third", "fourth", "fifth", "second", "third", "fourth", "fifth", "second", "third", "fourth", "fifth", "second", "third", "fourth", "fifth"
    };
    private ListView lv;
    private List<String> mList = new ArrayList<>();
    private MyAdapter mAdapter;View footView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadmore);
        for (int i =0;i<strs.length;i++){
            mList.add(strs[i]);
        }
        LayoutInflater inflater = LayoutInflater.from(this);
        footView = inflater.inflate(R.layout.content_main,null);
        footView.setVisibility(View.GONE);

        lv = (ListView) findViewById(R.id.list);
        lv.addFooterView(footView);
        mAdapter = new MyAdapter(mList,this);
        lv.setAdapter(mAdapter);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    footView.setVisibility(View.VISIBLE);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            for (int i = 0; i < 5; i++) {
                                mList.add(strs[i]);
                            }
                            handler.sendEmptyMessage(0);
                        }
                    }) {
                    }.start();
                }
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            mAdapter.setDataChanged(mList);
            footView.setVisibility(View.GONE);
        }
    };
}
