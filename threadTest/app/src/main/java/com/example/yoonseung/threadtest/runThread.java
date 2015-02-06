package com.example.yoonseung.threadtest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

// yoon // add bgn ...

import android.media.MediaPlayer;
import android.util.Log;

// yoon // add ... end

/**
 * Created by Yoonseung on 2015. 2. 1..
 */
public class runThread extends Service {

    private MediaPlayer mPlayer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d("slog", "onStart()");
        super.onStart(intent, startId);
        mPlayer = MediaPlayer.create(this, R.raw.tango);
        mPlayer.start();
    }

    @Override
    public void onDestroy() {
        Log.d("slog", "onDestroy()");
        mPlayer.stop();
        super.onDestroy();
    }
}
