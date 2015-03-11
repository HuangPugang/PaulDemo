package demo.hpg.org.pauldemo.lightcontrol;

import android.app.Activity;
import android.content.ContentResolver;
import android.provider.Settings;
import android.view.WindowManager;

/**
 * 用于亮度调节
 *
 * @author huarizhong
 *
 */
public class LightnessControl {
    /**
     * 是否为自动调节亮度
     *
     * @param activity
     * @return
     */
    public static boolean isAuto(Activity activity) {
        boolean autoBrightness = false;
        //使用ContentResolver获得系统的数据
        ContentResolver resolver = activity.getContentResolver();
        try {
            //判断是否为自动调节
            autoBrightness = Settings.System.getInt(resolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autoBrightness;
    }

    /**
     * 设置亮度值
     */
    public static void setLightness(Activity activity, int value) {

        try {
            Settings.System.putInt(activity.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS, value);
            WindowManager.LayoutParams lp = activity.getWindow()
                    .getAttributes();
            lp.screenBrightness = (value <= 0 ? 1 : value) / 255f;
            activity.getWindow().setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取亮度
     *
     * @param act
     * @return
     */
    public static int getLightness(Activity act) {
        return Settings.System.getInt(act.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, -1);
    }

    /**
     * 停止自动亮度调节
     *
     * @param activity
     */
    public static void stopAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    /**
     * 开启亮度自动调节
     *
     * @param activity
     */
    public static void startAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }
}
