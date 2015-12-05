package demo.hpg.org.pauldemo.media.single;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpDownloader implements Runnable{
	private String mFileUrl;
	private HttpDownloadHandler mDownloadHandler ;
	private String mPath = Environment.getExternalStorageDirectory() + "/Dabai/download/";
	private String mFileName;

	public HttpDownloader(String url,String filename ,HttpDownloadHandler downloadHandler){
		this.mFileUrl = url;
		this.mFileName = filename;
		this.mDownloadHandler = downloadHandler;
	}


	@Override
	public void run() {
		InputStream inputStream = null;
		String filePath = mPath + mFileName;
		try {
			File file = new File(filePath);
			Log.e("HPG","filePath="+filePath);
			if (file.exists()) {
				mDownloadHandler.onSuccess(filePath);
				Log.e("HPG","file.exists()");
			} else {
				URL url = new URL(mFileUrl);
				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
				inputStream = urlConn.getInputStream();
				File resultFile = write2SDFromInput(mPath,mFileName, inputStream);
				if (resultFile == null) {
					mDownloadHandler.onFailed();
				}
				Log.e("HPG","filePath="+filePath);
				mDownloadHandler.onSuccess(filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mDownloadHandler.onFailed();
		} finally {
			try {
				if (inputStream!=null) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 该函数返回整形 -1：代表下载文件出错 0：代表下载文件成功 1：代表文件已经存在
	 */
	public int downFile(String urlStr, String path, String fileName) {
		InputStream inputStream = null;
		try {
			FileUtils fileUtils = new FileUtils();

			if (fileUtils.isFileExist(path + fileName)) {
				return 1;
			} else {
				URL url = new URL(urlStr);
				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
				inputStream = urlConn.getInputStream();
				File resultFile = fileUtils.write2SDFromInput(path,fileName, inputStream);
				if (resultFile == null) {
					return -1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (inputStream!=null) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}


	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public File write2SDFromInput(String path,String fileName,InputStream input){
		File file = null;
		OutputStream output = null;
		try{
			File dir = new File(path);//路径不存在
			if (!dir.exists()) {
				dir.mkdirs();
			}
			file = new File(path + fileName);
			file.createNewFile();
			output = new FileOutputStream(file);
			byte buffer [] = new byte[4 * 1024];
			while((input.read(buffer)) != -1){
				output.write(buffer);
			}
			output.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				output.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 在SD卡上创建文件
	 *
	 * @throws IOException
	 */
	public File creatSDFile(String fileName) throws IOException {
		File file = new File(mPath + fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 在SD卡上创建目录
	 *
	 * @param dirName
	 */
	public File creatSDDir(String dirName) {
		File dir = new File(dirName);
		dir.mkdirs();
		return dir;
	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 */
	public boolean isFileExist(String fileName){
		File file = new File( fileName);
		return file.exists();
	}
}
