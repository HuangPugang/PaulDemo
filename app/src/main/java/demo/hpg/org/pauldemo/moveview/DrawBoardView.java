package demo.hpg.org.pauldemo.moveview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/11
 * Time 17:10
 */
public class DrawBoardView extends View {
    private Resources mResources;
    //画笔，定义绘制属性
    private Paint mPaint;
    private Paint mBitmapPaint;
    //绘制路径
    private Path mPath;
    //画布极其底层位图
    private Bitmap mBitmap;
    private Canvas mCanvas;


    private float mX,mY;
    private static final float TOUCH_TOLERANCE = 4;

    //记录宽度和高度
    private int mWidth;
    private int mHeight;

    public DrawBoardView(Context context) {
        super(context);
        initialize();
    }

    public DrawBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public DrawBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    /**
     * 初始化工作
     */
    private void initialize()
    {
        // Get a reference to our resource table.
        mResources = getResources();

        // 绘制自由曲线用的画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(mResources.getColor(R.color.green));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touch_start(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 背景颜色
        // canvas.drawColor(getResources().getColor(R.color.blue_dark));

        // 如果不调用这个方法，绘制结束后画布将清空
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

        // 绘制路径
        canvas.drawPath(mPath, mPaint);
    }

    private void touch_start(float x, float y)
    {
        mPath.reset();
        mPath.moveTo(x,y);
        mX = x;
        mY = y;
    }
    private void touch_move(float x,float y){
        float dx = Math.abs(x-mX);
        float dy = Math.abs(y-mY);
        if (dx>=TOUCH_TOLERANCE||dy>=TOUCH_TOLERANCE){
            mPath.quadTo(mX,mY,(x+mX)/2,(y+mY)/2);
            mX = x;
            mY = y;
        }
    }
    private void touch_up(){
        mPath.lineTo(mX,mY);
        // commit the path to our offscreen
        // 如果少了这一句，笔触抬起时myPath重置，那么绘制的线将消失
        mCanvas.drawPath(mPath,mPaint);
        // kill this so we don't double draw
        mPath.reset();
    }

    /**
     * 清除整个图像
     */
    public void clear()
    {
        // 清除方法1：重新生成位图
        // myBitmap = Bitmap
        // .createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        // myCanvas = new Canvas(myBitmap);

        // 清除方法2：将位图清除为白色
        mBitmap.eraseColor(mResources.getColor(R.color.white));
//        mCanvas.restore();
        // 两种清除方法都必须加上后面这两步：
        // 路径重置
        mPath.reset();
        // 刷新绘制
        invalidate();

    }
}
