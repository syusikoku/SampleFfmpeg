package com.kasaax.commons.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * Description：基类
 * Date：2019/7/25
 *
 */
public class BaseViewModel extends AndroidViewModel {
    protected final Application mCtx;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mCtx = application;
    }

}
