package demo.hpg.org.pauldemo.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import demo.hpg.org.pauldemo.R;

/**
 * Created by Paul on 15/11/23.
 */
public class BaseActivity extends AppCompatActivity {
    private SystemBarTintManager tintManager;

    private View mDecorView;

    private boolean hasHideSystemUi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initWindow();
    }


    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);

    }

    protected <T extends View> T fvById(int id) {
        // return返回view时,加上泛型T
        return (T) findViewById(id);
    }

    protected int getMyColor(int res){
        if (res <= 0)
            throw new IllegalArgumentException("resource id can not be less 0");
        return getResources().getColor(res);
    }
    @TargetApi(19)
    private void initWindow(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getMyColor(R.color.app_main_color));
            tintManager.setStatusBarTintEnabled(true);
        }
        mDecorView = getWindow().getDecorView();
    }

    @TargetApi(19)
    public void hideSystemUI() {
        hasHideSystemUi = true;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (mDecorView != null){
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }

        if (tintManager != null){
            tintManager.setStatusBarTintEnabled(false);
        }
    }

    @TargetApi(16)
    public void showSystemUI() {
        hasHideSystemUi = false;
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (mDecorView != null){
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (tintManager != null){
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    public boolean hasNavBar(){
        if (tintManager == null)
            return false;
        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
        return config.hasNavigtionBar();
    }

}
