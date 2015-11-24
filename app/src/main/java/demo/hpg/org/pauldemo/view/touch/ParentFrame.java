package demo.hpg.org.pauldemo.view.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class ParentFrame extends LinearLayout{

	public ParentFrame(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	/**
	 * super.dispatchTouchEvent(ev):由系统决定
	 * false:不再分发
	 * true:可以继续分发
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e("HPG", "ParentFrame - dispatchTouchEvent");
		return super.dispatchTouchEvent(ev);
	}
	
	/**
	 * super.onInterceptTouchEvent(ev):系统决定
	 * true:拦截
	 * false:不拦截
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.e("HPG", "ParentFrame - onInterceptTouchEvent");
		return false;
	}
	/**
	 * super.onTouchEvent(ev):由系统决定
	 * true:消耗此事件,不再分发给父view处理
	 * false:不消耗,继续分发给父view处理
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		Log.e("HPG", "ParentFrame - onTouchEvent");
		return super.onTouchEvent(ev);
	}

}
