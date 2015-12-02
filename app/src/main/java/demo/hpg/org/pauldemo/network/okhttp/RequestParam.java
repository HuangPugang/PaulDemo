package demo.hpg.org.pauldemo.network.okhttp;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Paul on 15/11/24.
 */
public class RequestParam {
    private static String ENCODING = "UTF-8";

    protected ConcurrentHashMap<String, String> urlParams;
    protected ConcurrentHashMap<String, FileWrapper> fileParams;
    protected ConcurrentHashMap<String, ArrayList<String>> urlParamsWithArray;
    public RequestParam(){
        init();
    }

    private void init(){
        urlParams = new ConcurrentHashMap<String, String>();
        fileParams = new ConcurrentHashMap<String, FileWrapper>();
        urlParamsWithArray = new ConcurrentHashMap<String, ArrayList<String>>();

    }



    private static class FileWrapper {
        public InputStream inputStream;
        public String fileName;
        public String contentType;

        public FileWrapper(InputStream inputStream, String fileName, String contentType) {
            this.inputStream = inputStream;
            this.fileName = fileName;
            this.contentType = contentType;
        }

        public String getFileName() {
            if(fileName != null) {
                return fileName;
            } else {
                return "nofilename";
            }
        }
    }
}
