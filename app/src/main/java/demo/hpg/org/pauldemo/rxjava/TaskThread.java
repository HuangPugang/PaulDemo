package demo.hpg.org.pauldemo.rxjava;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Paul on 15/11/27.
 */
public class TaskThread implements Runnable {

    private CallBack mCallback;

    private Object mObject;

    public TaskThread(CallBack c) {
        mCallback = c;
    }

    @Override
    public void run() {
        mObject = mCallback.runOnThread();
        if (mObject == null) {
            mCallback.failed();
        }
        Message msg = Message.obtain();
        msg.obj = mObject;
        uiHandler.sendMessage(msg);


    }

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Object object = msg.obj;
            mCallback.runOnMain(object);
        }
    };
}
