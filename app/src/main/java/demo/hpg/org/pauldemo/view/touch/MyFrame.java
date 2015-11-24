package demo.hpg.org.pauldemo.view.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyFrame extends LinearLayout{

	public MyFrame(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e("HPG", "MyFrame - dispatchTouchEvent");
		return super.dispatchTouchEvent(ev);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.e("HPG", "MyFrame - onInterceptTouchEvent");
		return super.onInterceptTouchEvent(ev);
	}
	

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		Log.e("HPG", "MyFrame - onTouchEvent");
		return super.onTouchEvent(ev);
	}

}
