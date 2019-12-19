package com.kasaax.commons.utils;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;

import androidx.annotation.Nullable;

/**
 * Description：使用Glide加载网络图片等比例缩放(https://www.cnblogs.com/jiangzhishan/p/9429154.html)
 * Date：2019/7/22
 */
public class TransfromationUtils extends ImageViewTarget<Bitmap> {
    private final ImageView mTarget;

    @Override
    protected void setResource(@Nullable Bitmap resource) {
        view.setImageBitmap(resource);

        // 获取宽高
        int width = resource.getWidth();
        int height = resource.getHeight();

        // 获取控件宽高
        int ivWidth = mTarget.getWidth();

        // 计算缩放比例
        float sy = (float) ((ivWidth * 0.1) / (float) (width * 0.1));
        // 计算图片等比缩放后的高
        int ivHeight = (int) (height * sy);
        ViewGroup.LayoutParams layoutParams = mTarget.getLayoutParams();
        layoutParams.height = ivHeight;
    }

    public TransfromationUtils(ImageView view) {
        super(view);
        this.mTarget = view;
    }
}