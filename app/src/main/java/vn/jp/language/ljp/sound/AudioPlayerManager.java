package vn.jp.language.ljp.sound;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.utils.Common;
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
        //test only
//        if(true)
//            return;

        try {

            m = new MediaPlayer();
            m.reset();
//            AssetFileDescriptor descriptor = getAssets().openFd("beepbeep.mp3");
            Log.i(TAG, "sound:" + filename);
            AssetFileDescriptor descriptor = context.getAssets().openFd(filename + ".mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            m.prepare();
            m.setVolume(1f, 1f);


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

    public void play2(String filename) {
        //test only
//        if(true)
//            return;

        try {

            m = new MediaPlayer();
            m.reset();
//            AssetFileDescriptor descriptor = getAssets().openFd("beepbeep.mp3");
            Log.i(TAG, "sound:" + filename);
//            AssetFileDescriptor descriptor = context.getAssets().openFd(filename + ".mp3");
//            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
//            String path_file_name = Environment.getExternalStorageDirectory().toString() + Constant.FOLDER_JLPT + "/" + filename;
            String path_file_name = Common.getPathFile(Constant.FOLDER_JLPT) +  "/" + filename;
            m.setDataSource(path_file_name);
//            descriptor.close();

            m.prepare();
            m.setVolume(1f, 1f);


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

    public boolean isPlaying() {
        try {
            if (m != null && m.isPlaying()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void pause() {
        try {
            if (m != null && m.isPlaying()) {
                m.pause();
            }
        } catch (Exception e) {
            Log.trace(e);
        }
    }

    public void start() {
        try {
            if (m != null) {
                m.start();
            }
        } catch (Exception e) {
            Log.trace(e);
        }
    }
}
