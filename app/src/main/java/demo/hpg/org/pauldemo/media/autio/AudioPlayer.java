package demo.hpg.org.pauldemo.media.autio;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Paul on 15/12/4.
 */
public class AudioPlayer {

    private MediaPlayer mPlayer;

    public AudioPlayer() {
        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });

        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.release();
                return false;
            }
        });
    }


    public void start(String url) {
        if (mPlayer != null) {
            try {
                mPlayer.setDataSource(url);
                mPlayer.prepare();
                mPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void pause() {
        if (mPlayer != null) {
            mPlayer.pause();
        }
    }

    public void stop() {
        if (mPlayer != null) {
            mPlayer.stop();
        }
    }

    public void release() {
        if (mPlayer != null) {
            mPlayer.release();
        }
    }

}
