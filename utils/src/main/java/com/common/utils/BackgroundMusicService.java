package com.common.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

public class BackgroundMusicService extends Service {

    MediaPlayer mp;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mp = MediaPlayer.create(getBaseContext(), R.raw.background_music);

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audioManager
                .getStreamVolume(AudioManager.STREAM_RING);
        System.out.println("volume is:" + currentVolume);

        audioManager
                .setStreamVolume(AudioManager.STREAM_RING, currentVolume, 0);

        audioManager.adjustVolume(AudioManager.ADJUST_LOWER, 0);
        // mp.setVolume(1.0f, 1.0f);
        mp.setLooping(true);
    }

    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println("onStart");
        mp.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
        mp.stop();
    }

    public void pauseMusic() {
        mp.pause();
    }

    public void resumeMusic() {
        mp.start();
    }

}
