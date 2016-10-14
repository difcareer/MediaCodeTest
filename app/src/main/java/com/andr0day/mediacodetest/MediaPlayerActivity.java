package com.andr0day.mediacodetest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;

public class MediaPlayerActivity extends Activity implements SurfaceHolder.Callback {
    private static String SAMPLE;
    private PlayerThread mPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SurfaceView sv = new SurfaceView(this);
        sv.getHolder().addCallback(this);
        setContentView(sv);

        String fileName = "video.mp4";
        FileUtils.copyAssetsToFiles(this, fileName);

        SAMPLE = new File(getFilesDir(), fileName).getAbsolutePath();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mPlayer == null) {
            mPlayer = new PlayerThread(holder);
            mPlayer.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mPlayer != null) {
            mPlayer.interrupt();
        }
    }

    private class PlayerThread extends Thread {
        private SurfaceHolder surfaceHolder;

        public PlayerThread(SurfaceHolder surface) {
            this.surfaceHolder = surface;
        }

        @Override
        public void run() {
            try {
                MediaPlayer mediaPlayer = MediaPlayer.create(MediaPlayerActivity.this, Uri.parse("file://" + SAMPLE), surfaceHolder);
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}