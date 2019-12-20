package com.kasaax.commons.utils;

import android.util.Log;

public class LogUtil {
    private static final String LOG_NAME = "dl";

    public static void loge(String _s) {
        Log.e(LOG_NAME, _s);
    }

    public static void loge(String _p, String _s) {
        Log.e(LOG_NAME, _p + _s);
    }
}
