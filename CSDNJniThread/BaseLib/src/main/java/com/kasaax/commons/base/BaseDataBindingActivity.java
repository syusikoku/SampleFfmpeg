package com.kasaax.commons.base;


import com.kasaax.commons.utils.LogUtil;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Description：
 * Date：2019/7/18
 */
public abstract class BaseDataBindingActivity<V extends ViewDataBinding> extends BaseAct {
    protected V mBinding;

    @Override
    protected boolean hasUseDatabinding() {
        return true;
    }

    @Override
    protected void injectContentView() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutResId());
    }

    protected void loge(String s) {
        LogUtil.loge(getClass().getSimpleName() + " " + s);
    }
}
