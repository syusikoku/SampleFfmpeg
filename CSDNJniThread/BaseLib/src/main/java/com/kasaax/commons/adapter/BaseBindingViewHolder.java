package com.kasaax.commons.adapter;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description：
 * Date：2019/7/22
 */
public class BaseBindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public T mBinding;

    public BaseBindingViewHolder(T _t) {
        super(_t.getRoot());
        this.mBinding = _t;
    }
}
