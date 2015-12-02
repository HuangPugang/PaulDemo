package demo.hpg.org.pauldemo.network.volley;

import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import demo.hpg.org.pauldemo.MyApplication;
import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/11
 * Time 11:31
 */
public class ImageManager {

    public static void download(String url,ImageView imageView){
        ImageLoader imageLoader = new ImageLoader(MyApplication.getRequestQueue(),new VolleyImageCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.drawable.ic_launcher, R.drawable.failed);
        imageLoader.get(url,listener);
    }
}
