package demo.hpg.org.pauldemo.view.requestlayout;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class PullToZoomScrollView extends ScrollView implements OnScrollListener{
    private  boolean isonce;//加载该View的布局时是否是第一次加载，是第一次就让其实现OnMeasure里的代码

    private LinearLayout mParentView;//布局的父布局，ScrollView内部只能有一个根ViewGroup，就是此View
    private ViewGroup mTopView;//这个是带背景的上半部分的View，下半部分的View用不到的

    private int mScreenHeight;//整个手机屏幕的高度，这是为了初始化该View时设置mTopView用的
    private int mScreenWidth;
    private int mTopViewHeight;//这个就是mTopView的高度

    private int mCurrentOffset=0;//当前右侧滚条顶点的偏移量。ScrollView右侧是有滚动条的，当下拉时，
    //滚动条向上滑，当向下滑动时，滚动条向下滑动。

    private float startY=0;//向下拉动要放大，手指向下滑时，点击的第一个点的Y坐标
    private boolean isBig;//是否正在向下拉放大上半部分View
    private boolean isTouchOne;//是否是一次连续的MOVE，默认为false,
    //在MoVe时，如果发现滑动标签位移量为0，则获取此时的Y坐标，作为起始坐标，然后置为true,为了在连续的Move中只获取一次起始坐标
    //当Up弹起时，一次触摸移动完成，将isTouchOne置为false
    private float distance=0;//向下滑动到释放的高度差

    //------弹性部分--------
    private ObjectAnimator oa;//这个是对象动画，这个在本View里很简单，也很独立，就在这里申明一下，后面有两个方法
    //两个方法是：setT(int t),reset()两个方法用到，其他都和它无关了。
    //第一个子view
    private View mFirstChild;
    //按下Y轴坐标
    private float mDownY;

    private Rect normal = new Rect();

    private boolean isAnimationFinish = true;
    
    private OnMyScrollListener mScrollListener;
    
    private float mCurrentAlpha = 0;
    
    /**
     * 初始化获取高度值，并记录
     * @param context
     * @param attrs
     */
    public PullToZoomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOverScrollMode(View.OVER_SCROLL_NEVER);
        WindowManager wm= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mScreenHeight=metrics.heightPixels;
        mScreenWidth=metrics.widthPixels;
//        mTopViewHeight=mScreenHeight/2-(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, context.getResources().getDisplayMetrics());
        mTopViewHeight=mScreenWidth;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            mFirstChild = getChildAt(0);
        }
    }
    
    public void setOnMyScrollListener(OnMyScrollListener listener){
    	this.mScrollListener = listener;
    }

    /**
     * 将记录的值设置到控件上，并只让控件设置一次
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(!isonce) {
            mParentView = (LinearLayout) this.getChildAt(0);
            mTopView = (ViewGroup) mParentView.getChildAt(0);
            mTopView.getLayoutParams().height = mTopViewHeight;
            isonce=true;
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action =ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                super.onTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                if(mCurrentOffset<=0){
                    isNeedMove();
                    if(!isTouchOne){
                        startY=ev.getY();
                        isTouchOne=true;
                    }
                    distance=ev.getY()-startY;
                    if(distance>0){
                        isBig=true;
                        setT((int)-distance/4);
                    }
                }else {
                    final float preY = mDownY == 0 ? ev.getY() : mDownY;
                    float currentY = ev.getY();
                    int deltaY = (int) (preY - currentY);
                    // 滚动
                    mDownY = currentY;
                    // 当滚动到最上或者最下时就不会再滚动，这时移动布局
                    if (isNeedMove()) {
                        if (normal.isEmpty()) {
                            Log.e("normal",mFirstChild.getLeft()+"  "+mFirstChild.getTop()+" i "+mFirstChild.getBottom()+"  "+mFirstChild.getRight()+"   "+mFirstChild.getHeight());
                            // 保存正常的布局位置
                            normal.set(mFirstChild.getLeft(), mFirstChild.getTop(), mFirstChild.getRight(), mFirstChild.getBottom());
                        }
                        // 移动布局(关键)
                        mFirstChild.layout(mFirstChild.getLeft(), mFirstChild.getTop() - deltaY / 4, mFirstChild.getRight(), mFirstChild.getBottom() - deltaY / 4);
//                        mFirstChild.scrollBy(0,deltaY);
                    } else {
                        super.onTouchEvent(ev);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isBig) {
                    reset();
                    isBig=false;
                }
                isTouchOne=false;

                mDownY = 0;
                if (isNeedAnimation()) {
                    animation();
                }
                super.onTouchEvent(ev);
            break;
        }
        return super.onTouchEvent(ev);
    }

    public void commOnTouchEvent(MotionEvent ev) {


    }
    /**
     * 对象动画要有的设置方法
     * @param t
     */
    public void setT(int t) {
        scrollTo(0, 0);
        if (1.0f+t/150.0f>0) {//计算alpha值
        	mCurrentAlpha = 1.0f+t/150.0f;
		}else {
			mCurrentAlpha = 0;
		}
        mScrollListener.onScroll(mCurrentAlpha,false);
        if (t < 0) {//如果是下拉，则重新计算第一个view的高度,看上就是拉伸的效果
            mTopView.getLayoutParams().height = mTopViewHeight-t;
            mTopView.requestLayout();
        }
    }

    /**
     * 主要用于释放手指后的回弹效果
     */
    private void reset() {

        mScrollListener.onScroll(mCurrentAlpha,true);
        
        if (oa != null && oa.isRunning()) {
            return;
        }
        oa = ObjectAnimator.ofInt(this, "t", (int)-distance / 4, 0);
        oa.setDuration(150);
        oa.start();
    }

    /**
     * 这个是设置向上滑动时，上半部分View滑动速度让其小于下半部分
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        mCurrentOffset = t;//右边滑动标签相对于顶端的偏移量
//        Log.e("OFFSET","onScrollChanged="+mCurrentOffset);
        //当手势上滑，则右侧滚动条下滑，下滑的高度小于TopView的高度，则让TopView的上滑速度小于DownView的上滑速度
        //DownView的上滑速度是滚动条的速度，也就是滚动的距离是右侧滚动条的距离
        //则TopView的速度要小，只需要将右侧滚动条的偏移量也就是t缩小一定倍数就行了。我这里除以2速度减小1倍
        if (t <= mTopViewHeight&&t>=0&&!isBig) {
            mTopView.setTranslationY(t / 2);//使得TopView滑动的速度小于滚轮滚动的速度
        }
        if(isBig){
            scrollTo(0,0);
        }

    }
    


    // 开启动画移动
    public void animation() {
        // 开启移动动画
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, normal.top - mFirstChild.getTop());
        animation.setDuration(200);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimationFinish = false;

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                mFirstChild.clearAnimation();
                // 设置回到正常的布局位置
                mFirstChild.layout(normal.left, normal.top, normal.right, normal.bottom);
                normal.setEmpty();
                isAnimationFinish = true;
            }
        });
        mFirstChild.startAnimation(animation);
    }

    // 是否需要开启动画
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    // 是否需要移动布局
    public boolean isNeedMove() {
        //getMeasuredHeight() 控件的原始高度  getHeight() 界面的实际高度
        int offset = mFirstChild.getMeasuredHeight() - getHeight();
//        Log.e("OFFSET","isNeedMove="+offset);
//        Log.e("TAG","getMeasuredHeight():"+mFirstChild.getMeasuredHeight()+"  getHeight():"+getHeight());
        //getScrollY() 控件顶部到屏幕顶部的高度
        int scrollY = getScrollY();
//        Log.e("TAG",scrollY+"");
        //判断到达顶部还是底部scrollY=0 到达顶部  scrollY=offset到达底部
//        if (scrollY == 0 || scrollY == offset) {
        if (getScrollY() + getHeight() >=  computeVerticalScrollRange())
        if(scrollY == offset){ //当
        	
            return true;
        }
        return false;
    }
    
    public interface OnMyScrollListener{
    	public void onScroll(float alpha, boolean isShow);
    }

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
//		L.e("HPG", "view.getTop()"+view.getTop()+"");
	}
}
