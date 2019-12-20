package com.kasaax.commons.base;

import com.kasaax.commons.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.ObservableList;

/**
 * Description：ObservableList 回调，统一处理监听，数据更新时，通知前台界面刷新
 * Date：2019/7/29
 */
public abstract class AbsOnListChangedCallbackImpl<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {
    private List<T> list = new ArrayList<>();

    @Override
    public void onChanged(ObservableList<T> sender) {
        LogUtil.loge("onChanged");
    }

    @Override
    public void onItemRangeChanged(ObservableList<T> sender, int positionStart, int itemCount) {
        LogUtil.loge("onItemRangeChanged");
    }

    @Override
    public void onItemRangeInserted(ObservableList<T> sender, int positionStart, int itemCount) {
        LogUtil.loge("onItemRangeInserted");

        list.clear();
        for (T t : sender) {
            list.add(t);
        }
        notifyChange(list);
    }

    /**
     * 通知界面刷新
     * @param _list
     */
    protected abstract void notifyChange(List<T> _list);

    @Override
    public void onItemRangeMoved(ObservableList<T> sender, int fromPosition, int toPosition,
                                 int itemCount) {
        LogUtil.loge("onItemRangeMoved");
    }

    @Override
    public void onItemRangeRemoved(ObservableList<T> sender, int positionStart, int itemCount) {
        LogUtil.loge("onItemRangeRemoved");
    }
}
