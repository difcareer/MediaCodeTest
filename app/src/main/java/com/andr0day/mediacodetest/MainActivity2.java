package com.andr0day.mediacodetest;

import android.app.Activity;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MainActivity2 extends Activity {
    private static String input = "video.mp4";
    private static String output = "output.mp4";

    private static String inputFile;
    private static String outputFile;

    private MediaExtractor mMediaExtractor;
    private MediaMuxer mMediaMuxer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileUtils.copyAssetsToFiles(this, input);

        inputFile = new File(getFilesDir(), input).getAbsolutePath();
        outputFile = new File(getFilesDir(), output).getAbsolutePath();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    protected boolean process() throws IOException {

        mMediaExtractor = new MediaExtractor();
        mMediaExtractor.setDataSource(inputFile);

        int mVideoTrackIndex = -1;
        int frameRate = 24;
        for (int i = 0; i < mMediaExtractor.getTrackCount(); i++) {
            MediaFormat format = mMediaExtractor.getTrackFormat(i);
            String mime = format.getString(MediaFormat.KEY_MIME);
            if (!mime.startsWith("video/")) {
                continue;
            }
//            frameRate = format.getInteger(MediaFormat.KEY_FRAME_RATE);
            mMediaExtractor.selectTrack(i);
            mMediaMuxer = new MediaMuxer(outputFile, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
            mVideoTrackIndex = mMediaMuxer.addTrack(format);
            mMediaMuxer.start();
        }

        if (mMediaMuxer == null) {
            return false;
        }

        MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
        info.presentationTimeUs = 0;
        ByteBuffer buffer = ByteBuffer.allocate(500 * 1024);
        while (true) {
            int sampleSize = mMediaExtractor.readSampleData(buffer, 0);
            if (sampleSize < 0) {
                break;
            }
            mMediaExtractor.advance();
            info.offset = 0;
            info.size = sampleSize;
            info.flags = MediaCodec.BUFFER_FLAG_SYNC_FRAME;
            info.presentationTimeUs += 1000 * 1000 / frameRate;
            mMediaMuxer.writeSampleData(mVideoTrackIndex, buffer, info);
        }

        mMediaExtractor.release();

        mMediaMuxer.stop();
        mMediaMuxer.release();

        return true;
    }


}