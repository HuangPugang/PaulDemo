package demo.hpg.org.pauldemo.media.single;

import java.io.File;

/**
 * Created by Paul on 15/12/3.
 */
public interface HttpDownloadHandler {

    public void onSuccess(String filePath);
    public void onFailed();
}
