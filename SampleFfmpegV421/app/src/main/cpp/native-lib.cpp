#include <jni.h>
#include <string>
#include <iostream>

using namespace std;

#include <stdio.h>

// log

#include <jni.h>
#include <android/log.h>

#define LOGI(FORMAT,...) __android_log_print(ANDROID_LOG_INFO,"zzg_ffmpeg",FORMAT,##__VA_ARGS__);
#define LOGE(FORMAT,...) __android_log_print(ANDROID_LOG_ERROR,"zzg_ffmpeg",FORMAT,##__VA_ARGS__);


extern "C"
JNIEXPORT jstring JNICALL
Java_com_zy_ffmpegv421_FfmpegUtils_stringFromJNI(JNIEnv *env, jclass clazz) {
    std::string hello = "Hello from C++";
    LOGE("call FfmpegUtils_stringFromJNI");
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_zy_ffmpegv421_FfmpegUtils_decode(JNIEnv *env, jclass clazz, jstring input,
                                          jstring output) {

}