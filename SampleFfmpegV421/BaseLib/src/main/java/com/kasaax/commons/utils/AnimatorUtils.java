package com.kasaax.commons.utils;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;


public class AnimatorUtils {
    private static final LinearOutSlowInInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();

    /**
     * 隐藏view
     */
    public static void scaleHide(View _view, ViewPropertyAnimatorListener _listener) {
        showTargetView(_view);
        ViewCompat.animate(_view)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(800)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(_listener)
                .start();
    }

    private static void showTargetView(View _view) {
        if (_view.getVisibility() != View.VISIBLE) {
            _view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示view
     */
    public static void scaleShow(View _view, ViewPropertyAnimatorListener _listener) {
        showTargetView(_view);
        ViewCompat.animate(_view)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f)
                .setDuration(800)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(_listener)
                .start();
    }

    public static void showHeight(final View _view, int _start, int _end) {
        showTargetView(_view);
        final ValueAnimator animator = ValueAnimator.ofFloat(_start, _end);
        final ViewGroup.LayoutParams lp = _view.getLayoutParams();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animator.getAnimatedValue();
                lp.height = (int) value;
                _view.setLayoutParams(lp);
            }
        });
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    public static void translation(final View _view, float _start, int _end) {
        showTargetView(_view);
        final ValueAnimator animator = ValueAnimator.ofFloat(_start, _end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animator.getAnimatedValue();
                _view.setTranslationY(value);
            }
        });
        animator.setDuration(200);
        animator.setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR);
        animator.start();
    }
}
