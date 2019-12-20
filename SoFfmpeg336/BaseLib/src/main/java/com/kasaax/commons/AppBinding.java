package com.kasaax.commons;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.kasaax.commons.utils.Utils;

import androidx.databinding.BindingAdapter;

/**
 * Description：databinding 辅助类，主要是对BindingAdapter整合操作
 * Date：2019/7/22
 */
public class AppBinding {
    /**
     * 图片加载，不支持图片圆角和placeholder
     */
    @BindingAdapter("imgUrl")
    public static void bindImgUrl(ImageView _imageView, String url) {
        Glide.with(_imageView.getContext())
                .load(url)
                .dontAnimate()
                .centerCrop()
                .into(_imageView);
    }

    /**
     * 图片加载，使用默认圆角大小,10dp
     */
    @BindingAdapter("imgUrlCircle")
    public static void bindImgUrlCircle(ImageView _imageView, String url) {
        Glide.with(_imageView.getContext()).load(url)
                .dontAnimate()
                .dontTransform()
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(Utils.dp2px(_imageView.getContext(), 10))))
                .into(_imageView);
    }

    /**
     * 图片加载: 支持placeHolder,和图片圆角
     * @param placeHolder : 使用方式: app:placeHolder="@{@drawable/default_park_infowindow_load_img}"
     * @param roundRadius : 使用方式: app:roundRadius="@{15}"
     */
    // https://blog.csdn.net/weixin_33728268/article/details/86781668
    @BindingAdapter(value = {"picUrl", "placeHolder", "roundRadius"}, requireAll = true)
    public static void bindImgUrlCircle(ImageView _imageView, String picUrl, Drawable placeHolder, int roundRadius) {
        RequestOptions opt = new RequestOptions();
        opt.placeholder(placeHolder);
        if (roundRadius == 0) {
            roundRadius = 10;
        }
        Glide.with(_imageView.getContext())
                .applyDefaultRequestOptions(opt)
                .load(picUrl)
                .dontAnimate()
                .dontTransform()
                .centerCrop()
                .apply(
                        RequestOptions.bitmapTransform(
                                new RoundedCorners(Utils.dp2px(_imageView.getContext(), roundRadius))
                        )
                )
                .into(_imageView);
    }


}
