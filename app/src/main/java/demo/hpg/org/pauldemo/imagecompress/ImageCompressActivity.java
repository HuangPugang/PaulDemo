package demo.hpg.org.pauldemo.imagecompress;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.utils.ImageCompress;

/**
 * Author huarizhong
 * Date 2015/3/17
 * Time 15:34
 */
public class ImageCompressActivity extends Activity {
    private String TAG = "ImageCompressActivity";
    private ImageView imageView;
    private Button sizeButton, qualityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_compress);
        imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.setImageDrawable(getResources().getDrawable(R.drawable.compress));

        sizeButton = (Button) findViewById(R.id.compress_size);
        qualityButton = (Button) findViewById(R.id.compress_quality);
        sizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.compress);
                Log.e(TAG,bitmap.getByteCount()+"");
                Bitmap bitmap2 = ImageCompress.comp(bitmap);
                imageView.setImageBitmap(bitmap2);
                Log.e(TAG,bitmap2.getByteCount()+"");
            }
        });
        qualityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.compress);
                Log.e(TAG,bitmap.getByteCount()+"");
                Bitmap bitmap2 = ImageCompress.compressImage(bitmap);
                imageView.setImageBitmap(bitmap2);
                Log.e(TAG,bitmap2.getByteCount()+"");
            }
        });
    }
}
