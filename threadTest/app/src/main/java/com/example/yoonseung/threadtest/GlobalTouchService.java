package com.example.yoonseung.threadtest;


        import android.app.Service;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.PixelFormat;
        import android.os.IBinder;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.View.OnTouchListener;
        import android.view.WindowManager;
        import android.widget.LinearLayout;
        import android.widget.LinearLayout.LayoutParams;

// yoon // add bgn ...

        import android.media.MediaPlayer;
        import android.util.Log;

// yoon // add ... end

public class GlobalTouchService extends Service implements OnTouchListener{


    private MediaPlayer mPlayer = null;
    private boolean nowPlaying = false;

    private float actionDown_ypos, actionUp_ypos;

    private String TAG = this.getClass().getSimpleName();
    // window manager
    private WindowManager mWindowManager;
    // linear layout will use to detect touch event
    private LinearLayout touchLayout;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        // create linear layout
        touchLayout = new LinearLayout(this);
        // set layout width 30 px and height is equal to full screen
        LayoutParams lp = new LayoutParams(30, LayoutParams.MATCH_PARENT);
        touchLayout.setLayoutParams(lp);
        // set color if you want layout visible on screen
//  touchLayout.setBackgroundColor(Color.CYAN);
        // set on touch listener
        touchLayout.setOnTouchListener(this);

        // fetch window manager object
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        // set layout parameter of window manager
        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(
                30, // width of layout 30 px
                WindowManager.LayoutParams.MATCH_PARENT, // height is equal to full screen
                WindowManager.LayoutParams.TYPE_PHONE, // Type Phone, These are non-application windows providing user interaction with the phone (in particular incoming calls).
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, // this window won't ever get key input focus
                PixelFormat.TRANSLUCENT);
        mParams.gravity = Gravity.LEFT | Gravity.TOP;
        Log.i(TAG, "add View");

        mWindowManager.addView(touchLayout, mParams);

    }


    @Override
    public void onDestroy() {
        if(mWindowManager != null) {
            if(touchLayout != null) {
                mWindowManager.removeView(touchLayout);
                if (nowPlaying == true) {
                    mPlayer.stop(); // yoon // add
                    nowPlaying = false;
                }
            }
        }
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            Log.i(TAG, "Action :" + event.getAction() + "\t X :" + event.getRawX() + "\t Y :" + event.getRawY() + "[Action Up]");
            actionUp_ypos = event.getRawY();

            // yoon // add bgn ...

            if (actionDown_ypos > actionUp_ypos && nowPlaying == false) { // yoon // 올리면 재생, 내리면 정지
                nowPlaying = true;
                mPlayer = MediaPlayer.create(this, R.raw.tango);
                mPlayer.start();
            } else if (nowPlaying == true) {
                mPlayer.stop(); // yoon // add
                nowPlaying = false;
            }

            // yoon // add ... end
        }
        else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.i(TAG, "Action :" + event.getAction() + "\t X :" + event.getRawX() + "\t Y :" + event.getRawY() + "[Action Down]");
            actionDown_ypos = event.getRawY();

        }
        return true;
    }

}