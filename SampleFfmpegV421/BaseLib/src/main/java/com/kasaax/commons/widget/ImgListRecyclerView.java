package com.kasaax.commons.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.kasaax.commons.utils.LogUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description：自定义的recyclerview，静止加载图片
 * Date：2019/7/25
 */
@BindingMethods({@BindingMethod(type = RecyclerView.class, attribute = "onScrollChanged", method =
        "processScrollChanged")})
public class ImgListRecyclerView extends RecyclerView {
    private final Context mCtx;

    public ImgListRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public ImgListRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImgListRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.mCtx = context;
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                LogUtil.loge("newState = " + newState);
                Glide.with(mCtx).pauseRequests();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(mCtx).resumeRequests();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


}
