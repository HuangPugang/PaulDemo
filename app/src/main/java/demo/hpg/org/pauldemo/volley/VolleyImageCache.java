package demo.hpg.org.pauldemo.volley;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Author huarizhong
 * Date 2015/3/11
 * Time 10:59
 */
public class VolleyImageCache implements ImageLoader.ImageCache {

    private LruCache<String,Bitmap> mCache;

    public VolleyImageCache(){
        int maxSize = 10*1024*1024;
        mCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }
    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}
