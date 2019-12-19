package com.zy.ffmpegv421;

import android.content.Context;

public class FfmpegUtils {
    private static Context context;

    static {
        System.loadLibrary("avcodec-57");
        System.loadLibrary("avfilter-6");
        System.loadLibrary("avformat-57");
        System.loadLibrary("avutil-55");
        System.loadLibrary("swresample-2");
        System.loadLibrary("swscale-4");
        System.loadLibrary("avdevice-57");
        System.loadLibrary("postproc-54");

        System.loadLibrary("native-lib");
    }

    public static void init(Context _context) {
        context = _context;
    }

    public static native String stringFromJNI();

    public static native void decode(String input, String output);

    public static native String printMetaData(String videoPath);
}
