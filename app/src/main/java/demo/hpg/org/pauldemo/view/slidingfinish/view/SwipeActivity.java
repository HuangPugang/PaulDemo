package demo.hpg.org.pauldemo.view.slidingfinish.view;

import android.app.Activity;
import android.os.Bundle;

public class SwipeActivity extends Activity {
	SwipeBackLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		layout = new SwipeBackLayout(this, null);
		layout.attachToActivity(this);
	}
}
