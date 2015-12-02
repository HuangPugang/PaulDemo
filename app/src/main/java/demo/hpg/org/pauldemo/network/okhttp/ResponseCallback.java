package demo.hpg.org.pauldemo.network.okhttp;

/**
 * Created by Paul on 15/11/23.
 */
public interface ResponseCallback {

    public void onSuccess(Object object , int tag);
    public void onFailed(int tag);

}
