package demo.hpg.org.pauldemo.media.autio;

import android.media.MediaPlayer;
import android.os.Bundle;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;
import demo.hpg.org.pauldemo.media.single.HttpDownloadHandler;
import demo.hpg.org.pauldemo.media.single.HttpDownloader;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

/**
 * Created by Paul on 15/12/3.
 */
public class AudioActivity extends BaseActivity {
    private Button btnPause, btnPlayUrl, btnStop;
    private SeekBar skbProgress;
    private String path;
    private MediaPlayer player2 = new MediaPlayer();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3player);

        this.setTitle("在线音乐播放");

        btnPlayUrl = (Button) this.findViewById(R.id.btnPlayUrl);
        btnPlayUrl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpDownloader httpDownloader = new HttpDownloader("http://www.mobvcasting.com/android/audio/goodmorningandroid.mp3", "xx.mp3", new HttpDownloadHandler() {
                    @Override
                    public void onSuccess(final String filePath) {
                        Log.e("HPG", "onSuccess");
                        try {
                            player2.setDataSource(filePath);
                            player2.prepare();
                            player2.start();
                            player2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    player2.release();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailed() {
                        Log.e("HPG", "onFailed");
                    }
                });
                new Thread(httpDownloader) {
                }.start();
            }
        });

        btnPause = (Button) this.findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                player2.pause();
            }
        });

        btnStop = (Button) this.findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                player2.release();
            }
        });


    }

    class ClickEvent implements OnClickListener {

        @Override
        public void onClick(View arg0) {


        }
    }

}

