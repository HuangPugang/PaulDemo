package demo.hpg.org.pauldemo.view.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class ChildFrame extends LinearLayout{

	public ChildFrame(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e("HPG", "ChildFrame - dispatchTouchEvent");
		return super.dispatchTouchEvent(ev);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.e("HPG", "ChildFrame - onInterceptTouchEvent");
		return super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.e("HPG", "ChildFrame - onTouchEvent");
		return super.onTouchEvent(event);
	}

}
