package vn.jp.language.ljp.sound;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import vn.jp.language.ljp.utils.Log;

/**
 * Created by Administrator on 1/23/2017.
 */

public class AudioPlayerManager {
    private static final String TAG = "AudioPlayerManager";
    MediaPlayer m = null;
    Context context;

    public AudioPlayerManager(Context context) {
        this.context = context;
        m = new MediaPlayer();
    }

    public void play(String filename) {
        try {
//            if (m != null && m.isPlaying()) {
//                m.stop();
//                m.release();
//            }
            m = new MediaPlayer();
            m.reset();
//            AssetFileDescriptor descriptor = getAssets().openFd("beepbeep.mp3");
            Log.i(TAG, "sound:" + filename);
            AssetFileDescriptor descriptor = context.getAssets().openFd(filename + ".mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            m.prepare();
            m.setVolume(1f, 1f);
//            m.setLooping(true);
//            m.start();

            m.setAudioStreamType(AudioManager.STREAM_MUSIC);

            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.release();
                }
            });
            m.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if (mp == m) {
                        m.start();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            if (m != null && m.isPlaying()) {
                m.stop();
                m.release();
            }
        } catch (Exception e) {
            Log.trace(e);
        }
    }
}
