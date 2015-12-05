package demo.hpg.org.pauldemo.media.multi;

/**
 * Created by Paul on 15/12/3.
 */

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 多线程文件下载
 *
 * @author yangxiaolong
 * @2014-8-7
 */
class DownloadTask extends Thread {
    private String TAG = "DownloadTask";
    private String downloadUrl;// 下载链接地址
    private int threadNum = 4 ;// 开启的线程数
    private String filePath;// 保存文件路径地址
    private int blockSize;// 每一个线程的下载量
    private DownloadHandler handler ;

    public DownloadTask(String downloadUrl, String fileptah,DownloadHandler handler){
        this.downloadUrl = downloadUrl;
        this.filePath = fileptah;
        this.handler = handler;

    }
    public DownloadTask(String downloadUrl, int threadNum, String fileptah,DownloadHandler handler) {
        this.downloadUrl = downloadUrl;
        this.threadNum = threadNum;
        this.filePath = fileptah;
        this.handler = handler;
    }

    @Override
    public void run() {

        FileDownloadThread[] threads = new FileDownloadThread[threadNum];
        try {
            URL url = new URL(downloadUrl);
            Log.d(TAG, "download file http path:" + downloadUrl);
            URLConnection conn = url.openConnection();
            // 读取下载文件总大小
            int fileSize = conn.getContentLength();
            if (fileSize <= 0) {
                handler.onFailed();
                return;
            }

            // 计算每条线程下载的数据长度
            blockSize = (fileSize % threadNum) == 0 ? fileSize / threadNum
                    : fileSize / threadNum + 1;

            Log.d(TAG, "fileSize:" + fileSize + "  blockSize:"+blockSize);

            File file = new File(filePath);
            for (int i = 0; i < threads.length; i++) {
                // 启动线程，分别下载每个线程需要下载的部分
                threads[i] = new FileDownloadThread(url, file, blockSize,
                        (i + 1));
                threads[i].setName("Thread:" + i);
                threads[i].start();
            }

            boolean isfinished = false;
            int downloadedAllSize = 0;
            while (!isfinished) {
                isfinished = true;
                // 当前所有线程下载总量
                downloadedAllSize = 0;
                for (int i = 0; i < threads.length; i++) {
                    downloadedAllSize += threads[i].getDownloadLength();
                    if (!threads[i].isCompleted()) {
                        isfinished = false;
                    }
                }
                // 通知handler去更新视图组件


                // Log.d(TAG, "current downloadSize:" + downloadedAllSize);
//					Thread.sleep(50);// 休息1秒后再读取下载进度
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            handler.onFailed();
        } catch (IOException e) {
            e.printStackTrace();
            handler.onFailed();
        } catch (Exception e) {
            e.printStackTrace();
            handler.onFailed();
        }

    }
}