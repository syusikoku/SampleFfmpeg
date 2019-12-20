package com.kasaax.commons.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.kasaax.commons.base.BaseApp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class Utils {
    public static String getMainThreadName() {
        return BaseApp.getMainThreadName();
    }

    public static int getAppProcessId() {
        return BaseApp.getAppProcessId();
    }

    public static Looper getAppLooper() {
        return BaseApp.getAppLooper();
    }

    public static BaseApp getAppInstance() {
        return BaseApp.getAppInstance();
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getAppHandler().postDelayed(runnable, delayMillis);
    }

    public static Handler getAppHandler() {
        return BaseApp.getAppHandler();
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getAppHandler().removeCallbacks(runnable);
    }

    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    // 判断当前的线程是不是在主线程
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getAppHandler().post(runnable);
    }

    public static int getMainThreadId() {
        return BaseApp.getMainThreadId();
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final int resId) {
        showToastSafe(ResourceUtils.getStr(resId));
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final String str) {
        if (isRunInMainThread()) {
            showToast(str);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(str);
                }
            });
        }
    }

    private static void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    public static Resources getResources(Context context) {
        return context.getResources();
    }

    public static Context getContext() {
        return BaseApp.getContext();
    }

    public static View inflateView(int resId, ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(resId, parent, false);
    }

    public static View inflateViewY(int resId, ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(resId, parent, true);
    }

    public static View inflateView(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null, false);
    }

    /**
     * 通过父局移除自己
     */
    public static void removeSelfByParent(View view) {
        if (view != null && view.getParent() != null && view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    /**
     *获取屏幕宽高
     * @param _context
     * @return
     */
    public static int[] getScreenWH(Context _context) {
        WindowManager wm = (WindowManager) _context.getSystemService(Context.WINDOW_SERVICE);
        Point tmpPoint = new Point();
        wm.getDefaultDisplay().getSize(tmpPoint);
        int[] points = new int[2];
        points[0] = tmpPoint.x;
        points[1] = tmpPoint.y;
        return points;
    }

    public static int dp2px(Context _context, float _dpValue) {
        final float scale = _context.getResources().getDisplayMetrics().density;
        return (int) (_dpValue * scale + 0.5f);
    }

    public static int px2dp(Context _context, float _pxValue) {
        final float scale = _context.getResources().getDisplayMetrics().density;
        return (int) (_pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param _context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context _context, float pxValue) {
        final float scale = _context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context _context, float spValue) {
        final float scale = _context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 获取两位小数
     * @param data
     * @return
     */
    public static float get2Double(double data) {
        return (float) Double.parseDouble(String.format("%.2f", data));
    }

    /**
     * 获取view在屏幕中的位置
     */
    public static int[] getLocation(View _view) {
        int[] out = new int[2];
        _view.getLocationOnScreen(out);
        return out;
    }

    /**
     * 获取控件宽高
     */
    public static int[] getWidthAndHeight(View _view) {
        int[] res = new int[2];
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        _view.measure(w, h);
        res[0] = _view.getMeasuredWidth();
        res[1] = _view.getMeasuredHeight();
        return res;
    }

    /**
     * 判断是否为空
     */
    public static <T> T checkIsNull(T _t) {
        if (_t == null) {
            throw new NullPointerException();
        }
        return _t;
    }

    /**
     * 是否是最后一行
     */
    public static boolean isLastRow(int _position, RecyclerView _parent) {
        int spanCount = getSpanCount(_parent);
        RecyclerView.LayoutManager layoutManager = _parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int itemCount = _parent.getAdapter().getItemCount();
            int lastRowCount = itemCount % spanCount;
            if (lastRowCount == 0 || lastRowCount < spanCount) {
                return true;
            }
        }
        return false;
    }

    private static int getSpanCount(RecyclerView _parent) {
        RecyclerView.LayoutManager layoutManager = _parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return 0;
    }

    /**
     * 是否是最后一列
     * @param _position
     * @param _parent
     * @return
     */
    public static boolean isLastColumn(int _position, RecyclerView _parent) {
        RecyclerView.LayoutManager layoutManager = _parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = getSpanCount(_parent);
            // position 从0开始，所以要+1
            if ((_position + 1) % spanCount == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 默认的LinearLayoutManager vertical 的分割线
     */
    public static RecyclerView.ItemDecoration getDefLinearVerticalItemDecoration(Context _context
            , int _pxSize) {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                       @NonNull RecyclerView parent,
                                       @NonNull RecyclerView.State state) {
                int top = Utils.dp2px(_context, _pxSize), bottom;
                if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                    bottom = 0;
                } else {
                    bottom = top;
                }
                outRect.set(top, top, top, bottom);
            }
        };
    }
}
