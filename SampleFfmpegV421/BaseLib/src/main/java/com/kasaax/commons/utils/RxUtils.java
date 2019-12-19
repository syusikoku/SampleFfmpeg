package com.kasaax.commons.utils;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description：
 * Date：2019/7/23
 */
public class RxUtils {
    /**
     * 统一线程处理
     */
    public static <T> FlowableTransformer<T, T> rxFlSchedulerHelper() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一线程处理
     */
    public static <T> ObservableTransformer<T, T> rxObsScheculerHelper() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 得到Observable
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception _e) {
                _e.printStackTrace();
            }
        });
    }

    /**
     * 得到Observable,参数支持列表
     */
    public static <T extends List> Observable<T> createListData(T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception _e) {
                _e.printStackTrace();
            }
        });
    }

    /**
     * 得到Flowable
     */
    public static <T> Flowable<T> createFlowableData(T t) {
        return Flowable.create(emitter -> {
            emitter.onNext(t);
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 支持列表创建
     */
    public static <T extends List> Flowable<T> createListFlowable(T t) {
        return Flowable.create(emitter -> {
            emitter.onNext(t);
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 得到Flowable
     */
    public static <T> Flowable<T> createFlowableData(List<T> t) {
        return Flowable.fromIterable(t);
    }
}
