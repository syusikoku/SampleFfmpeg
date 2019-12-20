package com.kasaax.commons.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * fragment基类
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected static Context mCtx;
    protected LayoutInflater mLayoutInflater;
    protected View mView;
    protected Activity mAct;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mCtx = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mAct = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = mLayoutInflater.inflate(getContentResId(), container, false);
        return mView;
    }

    protected abstract int getContentResId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        addListener();
    }

    protected void initView() {

    }

    protected void addListener() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        subInitListener();
    }

    protected void initData() {

    }

    protected void subInitListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
