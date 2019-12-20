package com.kasaax.commons.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Description：软键盘控制
 * Date：2019/7/25
 */
public class KeyBoardUtils {
    public static void openKeyboard(Context _context, EditText _editText) {
        InputMethodManager imm = (InputMethodManager) _context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.showSoftInput(_editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void closeKeyboard(Context _context, EditText _editText) {
        InputMethodManager imm = (InputMethodManager) _context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(_editText.getWindowToken(), 0);
    }

    public static void hideInput(Activity _activity) {
        InputMethodManager imm = (InputMethodManager) _activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View peekDecorView = _activity.getWindow().peekDecorView();
        if (null != peekDecorView) {
            imm.hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }
}
