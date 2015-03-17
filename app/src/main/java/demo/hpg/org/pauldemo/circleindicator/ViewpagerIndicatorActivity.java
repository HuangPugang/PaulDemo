package demo.hpg.org.pauldemo.circleindicator;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import org.hz.circleindicator.CircleIndicator;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import demo.hpg.org.pauldemo.R;

public class ViewpagerIndicatorActivity extends ActionBarActivity {
    private static final String TAG= "ViewpagerIndicatorActivity";
    private ScheduledExecutorService scheduledExecutorService;
    private int currentItem = 0;//当前页面

    private ViewPager defaultViewpager;
    private CircleIndicator defaultIndicator ;

    private ViewPager customViewpager ;
    private CircleIndicator customIndicator ;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_activity);

        // DEFAULT
        defaultViewpager = (ViewPager) findViewById(R.id.viewpager_default);
        defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
        DemoPagerAdapter defaultPagerAdapter = new DemoPagerAdapter(getSupportFragmentManager());
        defaultViewpager.setAdapter(defaultPagerAdapter);
        defaultIndicator.setViewPager(defaultViewpager);

        defaultViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    currentItem = position ;
                Log.e(TAG,position+"pagescrolled");
            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position ;
                Log.e(TAG,position+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // CUSTOM
        customViewpager = (ViewPager) findViewById(R.id.viewpager_custom);
        customIndicator = (CircleIndicator) findViewById(R.id.indicator_custom);
        DemoPagerAdapter customPagerAdapter = new DemoPagerAdapter(getSupportFragmentManager());
        customViewpager.setAdapter(customPagerAdapter);
        customIndicator.setViewPager(customViewpager);
        customIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override public void onPageScrolled(int i, float v, int i2) {

            }

            @Override public void onPageSelected(int i) {
                Log.d("OnPageChangeListener", "Current selected = " + i);
            }

            @Override public void onPageScrollStateChanged(int i) {

            }
        });

        // UNSELECTED BACKGROUND
        ViewPager unselectedBackgroundViewPager =
                (ViewPager) findViewById(R.id.viewpager_unselected_background);
        CircleIndicator unselectedBackgroundIndicator =
                (CircleIndicator) findViewById(R.id.indicator_unselected_background);
        DemoPagerAdapter unselectedBackgroundPagerAdapter =
                new DemoPagerAdapter(getSupportFragmentManager());
        unselectedBackgroundViewPager.setAdapter(unselectedBackgroundPagerAdapter);
        unselectedBackgroundIndicator.setViewPager(unselectedBackgroundViewPager);
    }

    class DemoPagerAdapter extends FragmentPagerAdapter {

        private int pagerCount = 5;

        private Random random = new Random();

        public DemoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override public Fragment getItem(int i) {
            return ColorFragment.newInstance(0xff000000 | random.nextInt(0x00ffffff));
        }

        @Override public int getCount() {
            return pagerCount;
        }


    }

    @Override
    protected void onStart() {
        //用一个定时器  来完成图片切换
        //Timer 与 ScheduledExecutorService 实现定时器的效果
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //通过定时器 来完成 每2秒钟切换一个图片
        //经过指定的时间后，执行所指定的任务
        //scheduleAtFixedRate(command, initialDelay, period, unit)
        //command 所要执行的任务
        //initialDelay 第一次启动时 延迟启动时间
        //period  每间隔多次时间来重新启动任务
        //unit 时间单位
         scheduledExecutorService.scheduleAtFixedRate(new ViewPagerTask(),1,3, TimeUnit.SECONDS);
        super.onStart();
    }

    @Override
    protected void onStop() {
        scheduledExecutorService.shutdown();
        super.onStop();
    }

    private class ViewPagerTask implements Runnable{

        @Override
        public void run() {
                currentItem = (currentItem+1)%5;
                handler.obtainMessage().sendToTarget();
        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            defaultViewpager.setCurrentItem((defaultViewpager.getCurrentItem()+1)%5);
            defaultIndicator.setViewPager(defaultViewpager);

//            customViewpager.setCurrentItem(currentItem);
//            customIndicator.setViewPager(customViewpager);
        }
    };
}