package com.zy.jthread;

public class ThreadTestHelper {
    static {
        System.loadLibrary("native-lib");
    }

    public static native String stringFromJNI();

    /**
     * 测试native中创建线程执行任务
     */
    public native void normalThread();

    /**
     * 测试消息任务: native中的生产者与消费者
     */
    public native void consumptionTask();
}
