package com.kasaax.commons.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.kasaax.commons.utils.LogUtil;

public class BaseApp extends Application {
    protected static BaseApp mAppInstance;
    protected static Context mContext;
    protected static int mThreadId;
    protected static String mThreadName;
    protected static Handler handler;
    protected static Looper looper;
    protected static int myPid;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppInstance = this;
        mContext = getApplicationContext();
        handler = new Handler();
        looper = getMainLooper();
        myPid = android.os.Process.myPid();
        mThreadId = android.os.Process.myTid();
        mThreadName = Thread.currentThread().getName();

        registerActivityLifecycleCallbacks(new BaseActivityLifecycleCallbackImpl() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                String simpleName = activity.getClass().getSimpleName();
                LogUtil.loge(simpleName + " onActivityCreated ");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                String simpleName = activity.getClass().getSimpleName();
                LogUtil.loge(simpleName + " onActivityDestroyed ");
            }
        });
    }


    public static String getMainThreadName() {
        return mThreadName;
    }

    public static int getAppProcessId() {
        return myPid;
    }

    public static int getMainThreadId() {
        return mThreadId;
    }

    public static Looper getAppLooper() {
        return looper;
    }

    public static Handler getAppHandler() {
        return handler;
    }

    public static BaseApp getAppInstance() {
        return mAppInstance;
    }

    public static Context getContext() {
        return mContext;
    }
}
