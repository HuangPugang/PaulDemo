package demo.hpg.org.pauldemo.view.stickscroll;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;

/**
 * Created by Paul on 15/11/25.
 */
public class StickyScrollActivity extends BaseActivity implements ScrollViewEx.IOnScrollListener {
    private LinearLayout linearLayout;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private List<View> lists = new ArrayList<View>();
    private ViewPager viewPager;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        ScrollViewEx scrollViewEx = (ScrollViewEx) findViewById(R.id.test);
        scrollViewEx.setOnScrollListener(this);
        linearLayout = (LinearLayout) findViewById(R.id.floatbar1);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        lists.add(getLayoutInflater().inflate(R.layout.layout1, null));
        lists.add(getLayoutInflater().inflate(R.layout.layout2, null));
        myAdapter = new MyAdapter(lists);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(myAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {                                 //当滑动式，顶部的imageView是通过animation缓慢的滑动


            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void onScroll(int t) {
        LinearLayout topView = (LinearLayout) findViewById(R.id.top);
        int topHeight = topView.getHeight();
        if (t > topHeight && linearLayout.getVisibility() == View.GONE) {
            linearLayout.setVisibility(View.VISIBLE);
        } else if (t <= topHeight && linearLayout.getVisibility() == View.VISIBLE) {
            linearLayout.setVisibility(View.GONE);
        }

    }

    public class MyAdapter extends PagerAdapter {

        List<View> viewLists;

        public MyAdapter(List<View> lists) {
            viewLists = lists;
        }

        @Override
        public int getCount() {                                                                 //获得size
            // TODO Auto-generated method stub
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View view, int position, Object object)                       //销毁Item
        {
            ((ViewPager) view).removeView(viewLists.get(position));
        }

        @Override
        public Object instantiateItem(View view, int position)                                //实例化Item
        {
            ((ViewPager) view).addView(viewLists.get(position), 0);
            return viewLists.get(position);
        }

    }
}
