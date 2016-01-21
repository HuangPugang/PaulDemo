package demo.hpg.org.pauldemo.imagecache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Cache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import libcore.io.DiskLruCache;

/**
 * Created by Paul on 15/12/27.
 * http://blog.csdn.net/lwyygydx/article/details/40401211
 * http://blog.csdn.net/xiaanming/article/details/9825113
 * http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg
 */
public class DiskCache {
    private DiskLruCache mDiskLruCache = null;
    private final int MAX_DISK_CACHE_SIZE =  10 * 1024 * 1024;
    private ImageView mImageView;
    public DiskCache(Context context){
        try {
            File cacheDir = getDiskCacheDir(context, "bitmap");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, MAX_DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        String path = cachePath + File.separator + uniqueName;
        Log.e("HPG",path);
        return new File(path);
    }


    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            Message message = new Message();
            message.obj = bitmap;
            handler.sendMessage(message);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public void download(ImageView imageView){
        this.mImageView = imageView;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
                String mykey = CacheUtils.hashKeyForDisk(imageUrl);
                if (getCache(mykey) != null) {
                    Log.e("HPG","hit");
                    mImageView.setImageBitmap(getCache(mykey));
                } else {
                    Log.e("HPG","getCache() is NULL");
                    try {
                        DiskLruCache.Editor editor = mDiskLruCache.edit(mykey);
                        if (editor != null) {
                            OutputStream outputStream = editor.newOutputStream(0);
                            if (downloadUrlToStream(imageUrl, outputStream)) {
                                editor.commit();
                            } else {
                                editor.abort();
                            }
                        }
                        mDiskLruCache.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap b = (Bitmap) msg.obj;
            mImageView.setImageBitmap(b);
        }
    };
    //
    public void saveCache(){

    }

    //从磁盘获得缓存
    public Bitmap getCache(String key){
        try {
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void removeCache(){
        try {
            String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
            String key = CacheUtils.hashKeyForDisk(imageUrl);
            mDiskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
