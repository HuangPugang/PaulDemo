package demo.hpg.org.pauldemo.optimize;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;

/**
 * Created by paul on 15/12/11.
 */
public class OptimizeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize);

        ImageView imageView = (ImageView) findViewById(R.id.iamgeview);
        Log.e("HPG",imageView.getLayoutParams().height+"--"+imageView.getLayoutParams().width);
        imageView.setImageBitmap(
                BitmapUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.ic_launcher, imageView.getLayoutParams().height, imageView.getLayoutParams().width));
    }
}
