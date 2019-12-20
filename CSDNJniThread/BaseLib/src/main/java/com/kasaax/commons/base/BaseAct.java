package com.kasaax.commons.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseAct extends AppCompatActivity implements View.OnClickListener {
    protected final String P = this.getClass().getSimpleName() + " ";
    protected Context mCtx;
    protected WeakHandler mWeakHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        beforeOnCreate();

        super.onCreate(savedInstanceState);

        if (hasUseDatabinding()) {
            injectContentView();
            beforeSetContentView();
            initDataBinding();
        } else {
            int layoutResId = getLayoutResId();
            if (layoutResId == 0) {
                throw new IllegalArgumentException("资源id不能为0");
            }
            beforeSetContentView();

            setContentView(layoutResId);
        }

        this.mCtx = this;
        mWeakHandler = new WeakHandler(this);
        beforeInit();
        initView();
        addListener();
        initData();
        refreshUi();
    }

    protected void injectContentView() {
        // TODO: 2019/6/24 调用setcontentview
    }

    protected void initDataBinding() {

    }

    protected boolean hasUseDatabinding() {
        return false;
    }

    protected void beforeOnCreate() {

    }

    protected abstract int getLayoutResId();

    protected void beforeSetContentView() {
    }

    protected void beforeInit() {

    }

    protected void initView() {

    }

    protected void addListener() {

    }

    protected void initData() {

    }

    protected void refreshUi() {

    }

    @Override
    public void onClick(View v) {

    }

    public void forward(Class<? extends BaseAct> _tCls) {
        if (_tCls != null) {
            startActivity(new Intent(mCtx, _tCls));
        }
    }

    public void forwardAppCompat(Class<? extends AppCompatActivity> _tCls) {
        if (_tCls != null) {
            startActivity(new Intent(mCtx, _tCls));
        }
    }

    @Override
    protected void onDestroy() {
        if (mWeakHandler != null) {
            mWeakHandler.removeCallbacksAndMessages(null);
            mWeakHandler = null;
        }
        super.onDestroy();
    }

    protected void showToast(String _s) {
        showShortToast(_s);
    }

    protected void showLongToast(String _s) {
        Toast.makeText(mCtx, _s, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String _s) {
        Toast.makeText(mCtx, _s, Toast.LENGTH_SHORT).show();
    }
}
