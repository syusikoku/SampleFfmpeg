package com.kasaax.commons.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * Description：图片高斯模式工具类
 *
 *   https://blog.csdn.net/xinpengfei521/article/details/74856848
 * Date：2019/7/30
 */
public class BlurBitmapUtil {
    // 图片缩放比例(即模糊度)
    private static final float BITMAP_SCALE = 0.4f;

    /**
     * 图片高斯模糊
     */
    public static Bitmap blurBitmap(Context _context, Bitmap _bitmap, float blurRadius) {
        // 计算图片缩小后的w&h
        int w = Math.round(_bitmap.getWidth() * BITMAP_SCALE);
        int h = Math.round(_bitmap.getHeight() * BITMAP_SCALE);

        // 将缩小的图片做为预渲染的图片
        Bitmap inputBmp = Bitmap.createScaledBitmap(_bitmap, w, h, false);
        // 创建一张渲染后的图片
        Bitmap outputBmp = Bitmap.createBitmap(inputBmp);

        // 创建RenderScript内核对象
        RenderScript renderScript = RenderScript.create(_context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript));

        // 由于RenderScript并没有使用VM来分配 内存，所以需要使用Allocation类来创建和分配 内存空间
        // 创建Allocation对象的时候其实内存是空的需要使用CopyTo将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(renderScript, inputBmp);
        Allocation tmpOut = Allocation.createFromBitmap(renderScript, outputBmp);
        // 设置渲染的模糊程序,25是最大模糊
        blurScript.setRadius(blurRadius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);

        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBmp);
        return outputBmp;
    }
}
