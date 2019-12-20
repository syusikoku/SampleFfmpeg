package com.zy.ffmpegv421;

import com.kasaax.commons.base.BaseApp;

public class FfmpegApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();

        FfmpegUtils.init(this);
    }
}
