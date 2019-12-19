#include <jni.h>
#include <string>
#include <iostream>

using namespace std;

#include <stdio.h>

extern "C" {
#include "libavformat/avformat.h"
#include "libavutil/dict.h"
}

// log

#include <jni.h>
#include <android/log.h>

#define LOGI(FORMAT, ...) __android_log_print(ANDROID_LOG_INFO,"zzg_ffmpeg",FORMAT,##__VA_ARGS__);
#define LOGE(FORMAT, ...) __android_log_print(ANDROID_LOG_ERROR,"zzg_ffmpeg",FORMAT,##__VA_ARGS__);


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

extern "C"
JNIEXPORT jstring JNICALL
Java_com_zy_ffmpegv421_FfmpegUtils_printMetaData(JNIEnv *env, jclass clazz, jstring video_path) {

    av_register_all();

    AVFormatContext *fmt_ctx = NULL;
    AVDictionaryEntry *tag = NULL;

    int ret;

    jboolean isCopy = false;
    const char *path = env->GetStringUTFChars(video_path, &isCopy);
    LOGE("video path: %s\n", path);

    if ((ret = avformat_open_input(&fmt_ctx, path, NULL, NULL))) {
        LOGE("avformat_open_input fail, ret :%d", ret);
        return env->NewStringUTF("avformat_open_input fail");
    }

    if ((ret = avformat_find_stream_info(fmt_ctx, NULL)) < 0) {
        LOGE("avformat_find_stream_info fail, ret :%d", ret);
        av_log(NULL, AV_LOG_ERROR, "Cannot find stream information\n");
        return env->NewStringUTF("avformat_find_stream_info fail");
    }

    std::string buf(path);
    buf += "\n";

    while ((tag = av_dict_get(fmt_ctx->metadata, "", tag, AV_DICT_IGNORE_SUFFIX))) {
        LOGE("%s=%s\n", tag->key, tag->value);
        buf += tag->key;
        buf += "=";
        buf += tag->value;
        buf += "\n";
    }

    avformat_close_input(&fmt_ctx);

    env->ReleaseStringUTFChars(video_path, path);

    LOGE("%s\n", "printMetaData end");

    return env->NewStringUTF(buf.c_str());
}