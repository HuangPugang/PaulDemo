package demo.hpg.org.pauldemo.view.slidingfinish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import demo.hpg.org.pauldemo.R;

public class SlidingActivity extends SwipeBackActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sliding);
		

		Button mButtonNormal = (Button) findViewById(R.id.normal_activity);
		mButtonNormal.setOnClickListener(this);

		Button mButtonAbs = (Button) findViewById(R.id.absListview_activity);
		mButtonAbs.setOnClickListener(this);

		Button mButtonScroll = (Button) findViewById(R.id.scrollview_activity);
		mButtonScroll.setOnClickListener(this);

		Button mButtonViewPager = (Button) findViewById(R.id.viewpager_activity);
		mButtonViewPager.setOnClickListener(this);

	}
	

	@Override
	public void onClick(View v) {
		Intent mIntent = null;
		switch (v.getId()) {
		case R.id.normal_activity:
			mIntent = new Intent(SlidingActivity.this, NormalActivity.class);
			break;
		case R.id.absListview_activity:
			mIntent = new Intent(SlidingActivity.this, AbsActivity.class);
			break;
		case R.id.scrollview_activity:
			mIntent = new Intent(SlidingActivity.this, ScrollActivity.class);
			break;
		case R.id.viewpager_activity:
			mIntent = new Intent(SlidingActivity.this, ViewPagerActivity.class);
			break;
		}

		startActivity(mIntent);
	}
	

}
