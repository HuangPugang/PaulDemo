package demo.hpg.org.pauldemo.system.lightcontrol;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import demo.hpg.org.pauldemo.R;


/**
 * 修改当前页面的亮度
 * Author huarizhong
 * Date 2015/3/11
 * Time 9:04
 */
public class LightControlActivity extends Activity {
    // 调节亮度的进度条
    private SeekBar seekBar;
    // 系统自身亮度
    int sysBrightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lightcontrol);
        // 获得系统亮度
        sysBrightness = LightnessControl.getLightness(this);
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        seekBar.setProgress(sysBrightness);
        // 监听进度条的变化
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //进度条发生变化调节亮度
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                LightnessControl.setLightness(LightControlActivity.this, progress);
            }
        });

    }
}