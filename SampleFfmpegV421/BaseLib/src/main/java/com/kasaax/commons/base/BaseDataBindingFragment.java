package com.kasaax.commons.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Description：
 * Date：2019/7/23
 */
public abstract class BaseDataBindingFragment<V extends ViewDataBinding> extends BaseFragment {
    protected V mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(mLayoutInflater, getContentResId(), container, false);
        mView = mBinding.getRoot();
        injectDataBinding();
        return mView;
    }

    protected abstract void injectDataBinding();
}
