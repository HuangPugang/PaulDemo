package demo.hpg.org.pauldemo.view.autolayout;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author Paul
 * @Description:自动换行布局
 * @date 2015-10-20 下午1:01:21 
 * @version
 */
public class AutoLayout extends ViewGroup {

	private final static String TAG = "AutoLayout";

	private final static int VIEW_MARGIN_V = 15;
	
	private final static int VIEW_MARGIN_H=20;

	public AutoLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public AutoLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public AutoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// Log.d(TAG, "widthMeasureSpec = " + widthMeasureSpec+
		// " heightMeasureSpec" + heightMeasureSpec);
		for (int index = 0; index < getChildCount(); index++) {
			final View child = getChildAt(index);
			// measure
			child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final int count = getChildCount();
		int row = 0;// which row lay you view relative to parent
		int lengthX = l; // right position of child relative to parent
		int lengthY = t; // bottom position of child relative to parent
		for (int i = 0; i < count; i++) {

			final View child = this.getChildAt(i);
			int width = child.getMeasuredWidth();
			int height = child.getMeasuredHeight();
			lengthX += width + VIEW_MARGIN_V;
			lengthY = row * (height + VIEW_MARGIN_H) + VIEW_MARGIN_H + height + t;
			// if it can't drawing on a same line , skip to next line
			if (lengthX > r) {
				lengthX = width + VIEW_MARGIN_V + l;
				row++;
				lengthY = row * (height + VIEW_MARGIN_H) + VIEW_MARGIN_H + height
						+ t;
			
			}

			child.layout(lengthX - width, lengthY - height, lengthX, lengthY);
		}

	}

}
