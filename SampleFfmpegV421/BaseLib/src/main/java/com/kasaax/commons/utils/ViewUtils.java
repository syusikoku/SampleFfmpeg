package com.kasaax.commons.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import com.kasaax.commons.AppConst;

public class ViewUtils {
    public static void setViewMargin(View _view, Rect _rect) {
        if (_view == null || _rect == null) {
            return;
        }
        ViewGroup.LayoutParams vlp = _view.getLayoutParams();
        if (vlp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams vgmlp = (ViewGroup.MarginLayoutParams) vlp;
            vgmlp.leftMargin = _rect.left;
            vgmlp.topMargin = _rect.top;
            vgmlp.rightMargin = _rect.right;
            vgmlp.bottomMargin = _rect.bottom;
            _view.setLayoutParams(vgmlp);
        }
    }

    public static void setTime(String _flag, Context _context) {
        SharedPreferences sharedPreferences = _context.getSharedPreferences(AppConst.SP_INTERNAL_CACHE,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(_flag, System.currentTimeMillis()).commit();
    }

    public static String getTime(String _flag, Context _context) {
        SharedPreferences sharedPreferences = _context.getSharedPreferences(AppConst.SP_INTERNAL_CACHE,
                Context.MODE_PRIVATE);
        long time = sharedPreferences.getLong(_flag, 0);
        return DateTimeUtils.formatDateTime(time);
    }
}
