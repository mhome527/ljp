package vn.jp.language.ljp.sound;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import vn.jp.language.ljp.utils.Log;

/**
 * Created by Administrator on 1/23/2017.
 */

public class AudioManager {
    private static final String TAG = "AudioManager";
    MediaPlayer m = null;
    Context context;

    public AudioManager(Context context) {
        this.context = context;
        m = new MediaPlayer();
    }

    public void play(String filename) {
        try {
            if (m.isPlaying()) {
                m.stop();
                m.release();
            }
            m = new MediaPlayer();
//            AssetFileDescriptor descriptor = getAssets().openFd("beepbeep.mp3");
            Log.i(TAG, "sound:" + filename);
            AssetFileDescriptor descriptor = context.getAssets().openFd(filename + ".mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            m.prepare();
            m.setVolume(1f, 1f);
//            m.setLooping(true);
            m.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
