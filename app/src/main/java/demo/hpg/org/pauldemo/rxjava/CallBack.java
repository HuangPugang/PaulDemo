package demo.hpg.org.pauldemo.rxjava;

/**
 * Created by Paul on 15/11/27.
 */
public interface CallBack {
    /**
     * 耗时操作在这里进行，比如说读取文件，数据库操作，网络操作等
     * @return 耗时操作得到的值
     */
    public Object runOnThread();

    /**
     * 耗时操作完成后在这里进行操作
     * @param object 耗时操作得到值
     */
    public void runOnMain(Object object);

    /**
     * 操作失败
     */
    public void failed();
}
