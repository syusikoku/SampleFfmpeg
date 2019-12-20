package com.zy.jthread;

public class ThreadTestHelper {
    static {
        System.loadLibrary("native-lib");
    }

    private OnErrorListener mOnErrorListener;

    public static native String stringFromJNI();

    /**
     * 测试native中创建线程执行任务
     */
    public native void normalThread();

    /**
     * 测试消息任务: native中的生产者与消费者
     */
    public native void consumptionTask();

    public void setOnErrorListener(OnErrorListener listener) {
        this.mOnErrorListener = listener;
    }

    public interface OnErrorListener {
        void onError(int code, String msg);
    }

    public native void callErrorListener();

    /**
     * 由native分别在主线程和子线程中测试调用
     * @param code
     * @param msg
     */
    public void onError(int code, String msg) {
        if (mOnErrorListener != null) {
            mOnErrorListener.onError(code, msg);
        }
    }
}
