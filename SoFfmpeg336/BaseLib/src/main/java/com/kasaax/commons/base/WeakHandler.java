package com.kasaax.commons.base;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Description：
 * Date：2019/7/18
 */
public class WeakHandler extends Handler {

    private WeakReference<Activity> mActivityWeakReference;

    public WeakHandler(Activity _activity) {
        mActivityWeakReference = new WeakReference<>(_activity);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mActivityWeakReference.get() != null && !mActivityWeakReference.get().isFinishing()) {
            super.handleMessage(msg);
        } else {
            mActivityWeakReference.clear();
        }
    }
}
