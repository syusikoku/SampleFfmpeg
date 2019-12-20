#include <jni.h>
#include <string>
#include "AndroidLog.h"
#include "mock_consumption_task.h"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_zy_jthread_ThreadTestHelper_stringFromJNI(JNIEnv *env, jclass clazz) {
    // TODO: implement stringFromJNI()
    LOGE("native exec stringFromJni");
    std::string str = "hello from C++";
    return env->NewStringUTF(str.c_str());
}

#include <pthread.h>

pthread_t thread;

/**
 * 回调接口 : 相当于java中的Runnable接口
 */
void *normalCallback(void *data) {
    LOGE("native exec normalCallback");
    LOGE("create normal thread from C++");
    // 退出线程
    pthread_exit(&thread);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_zy_jthread_ThreadTestHelper_normalThread(JNIEnv *env, jobject thiz) {
    LOGE("native exec nomalThread");
    // 创建线程
    pthread_create(&thread, NULL, normalCallback, NULL);
}


extern "C"
JNIEXPORT void JNICALL
Java_com_zy_jthread_ThreadTestHelper_consumptionTask(JNIEnv *env, jobject thiz) {
    LOGE("native exec consumptiontask...");
    runMockConsumptionTask();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_zy_jthread_ThreadTestHelper_callErrorListener(JNIEnv *env, jobject thiz) {


}