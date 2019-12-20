package com.kasaax.commons.base;

import com.kasaax.commons.utils.LogUtil;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;

/**
 * Description：
 * Date：2019/7/23
 */
public abstract class BasicFlowableSubscriber<T> implements FlowableSubscriber<T> {
    private Subscription mS;

    @Override
    public void onSubscribe(Subscription s) {
        this.mS = s;
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onError(Throwable t) {
        LogUtil.loge("msg = " + t.getMessage());
    }

    @Override
    public void onComplete() {
        if (mS != null) {
            mS.cancel();
            mS = null;
        }
    }
}
