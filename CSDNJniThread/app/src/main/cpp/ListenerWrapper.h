//
// Created by ubt on 2019/12/20.
//

#include <jni.h>

#ifndef CSDNJNITHREAD_LISTENERWRAPPER_H
#define CSDNJNITHREAD_LISTENERWRAPPER_H


class ListenerWrapper {
public:
    JavaVM *jvm;
    _JNIEnv *env;
    jobject jobj;
    jmethodID jmid;

public:
    ListenerWrapper(JavaVM *_jvm, _JNIEnv *_env, jobject obj);

    ~ListenerWrapper();
};


#endif //CSDNJNITHREAD_LISTENERWRAPPER_H
