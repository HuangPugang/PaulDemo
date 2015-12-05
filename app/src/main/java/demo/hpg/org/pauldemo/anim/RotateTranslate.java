package demo.hpg.org.pauldemo.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class RotateTranslate extends Animation {
    /**
     * 值为true时可明确查看动画的旋转方向。
     */
    public static final boolean DEBUG = false;
    /**
     * 沿Y轴正方向看，数值减1时动画逆时针旋转。
     */
    public static final boolean ROTATE_DECREASE = true;
    /**
     * 沿Y轴正方向看，数值减1时动画顺时针旋转。
     */
    public static final boolean ROTATE_INCREASE = false;
    /**
     * Z轴上最大深度。
     */
    public static final float DEPTH_Z = 0.0f;
    /**
     * 动画显示时长。
     */
    public static final long DURATION = 800l;
    /**
     * 图片翻转类型。
     */
    private final boolean type;
    private final float centerX;
    private final float centerY;
    private Camera camera;


    public RotateTranslate(float cX, float cY, boolean type) {
        centerX = cX;
        centerY = cY;
        this.type = type;
        setDuration(DURATION);
    }

    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        // 在构造函数之后、getTransformation()之前调用本方法。
        super.initialize(width, height, parentWidth, parentHeight);
        camera = new Camera();
    }



    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        // interpolatedTime:动画进度值，范围为[0.0f,10.f]

        float from = 0.0f, to = 0.0f;
        from = 0.0f;
        to = 180.0f;

        float degree = 180 * interpolatedTime;

        final Matrix matrix = transformation.getMatrix();
        camera.save();
        camera.translate(degree, 0.0f, -degree);
        //rotateX  X轴旋转
        //rotateY  Y轴旋转
        camera.getMatrix(matrix);
        camera.restore();
//        if (DEBUG) {
//            if (overHalf) {
//                matrix.preTranslate(-centerX * 2, -centerY);
//                matrix.postTranslate(centerX * 2, centerY);
//            }
//        } else {
//            //确保图片的翻转过程一直处于组件的中心点位置
//            matrix.preTranslate(-centerX, -centerY);
//            matrix.postTranslate(centerX, centerY);
//        }
    }

}
