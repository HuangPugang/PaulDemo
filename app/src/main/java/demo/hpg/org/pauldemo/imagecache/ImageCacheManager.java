package demo.hpg.org.pauldemo.imagecache;

/**
 * Created by paul on 15/12/28.
 */
public class ImageCacheManager {
    private DiskCache mDiskCache;
    private ImageDownLoader downLoader;
    private static ImageCacheManager sInstance = null;
    private ImageCacheManager(){

    }

    public static ImageCacheManager getInstance(){
        if (sInstance==null){
            synchronized (ImageCacheManager.class){
                sInstance = new ImageCacheManager();
            }
        }
        return sInstance;
    }



}
