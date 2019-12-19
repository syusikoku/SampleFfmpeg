package com.kasaax.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期工具类
 */
public class DateTimeUtils {
    /**
     * 格式化时间
     */
    public static String formatDateTime(long _datetime) {
        Date tmpDate = new Date(_datetime);
        String txt = "";
        if (isSameDay(_datetime)) {
            Calendar calendar = GregorianCalendar.getInstance();
            long tmpTimeInMillis = calendar.getTimeInMillis();
            if (isOnOneMinute(_datetime, tmpTimeInMillis)) {
                return "刚刚";
            } else if (isOnOneHour(_datetime, tmpTimeInMillis)) {
                return String.format("%d分钟之前", Math.abs(_datetime - tmpTimeInMillis) / (60 * 1000));
            } else {
                calendar.setTime(tmpDate);
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                if (hourOfDay > 17) {
                    txt = "晚上 hh:mm";
                } else if (hourOfDay >= 0 && hourOfDay <= 6) {
                    txt = "凌晨 hh:mm";
                } else if (hourOfDay > 11 && hourOfDay <= 17) {
                    txt = "下午 hh:mm";
                } else {
                    txt = "上午 hh:mm";
                }
            }
        } else if (isYesterDay(_datetime)) {
            txt = "昨天 HH:mm";
        } else if (isSameYear(_datetime)) {
            txt = "M月d日 HH:mm";
        } else {
            txt = "很久以前";
        }
        // 注意，如果使用android.text.format.DateFormat这个工具类，在API 17之前它只支持adEhkMmszy
        return new SimpleDateFormat(txt, Locale.CHINA).format(tmpDate);
    }

    /**
     * 是否在1分之内
     */
    private static boolean isOnOneMinute(long t1, long t2) {
        return Math.abs(t1 - t2) < 60 * 1000;
    }

    /**
     * 是否在1小时之内
     */
    private static boolean isOnOneHour(long t1, long t2) {
        return Math.abs(t1 - t2) < 1000 * 60 * 60;
    }

    /**
     * 是否是同一年
     */
    private static boolean isSameYear(long _datetime) {
        Calendar startCal;
        startCal = floorDay(Calendar.getInstance());
        // 1月份开始
        startCal.set(Calendar.MONTH, Calendar.JANUARY);
        // 第1天
        startCal.set(Calendar.DAY_OF_MONTH, 1);
        return _datetime >= startCal.getTimeInMillis();
    }

    /**
     * 是否是昨天
     */
    private static boolean isYesterDay(long _datetime) {
        Calendar startCal;
        startCal = floorDay(Calendar.getInstance());
        startCal.add(Calendar.DAY_OF_MONTH, -1);
        // 昨天的开始时间
        long startTime = startCal.getTimeInMillis();

        Calendar endCal;
        endCal = ceilDay(Calendar.getInstance());
        endCal.add(Calendar.DAY_OF_MONTH, -1);
        // 昨天的结束时间
        long endTime = endCal.getTimeInMillis();
        return _datetime > startTime && _datetime < endTime;
    }

    /**
     * 是否是同一天
     */
    private static boolean isSameDay(long _time) {
        long startTime = floorDay(Calendar.getInstance()).getTimeInMillis();
        long endTime = ceilDay(Calendar.getInstance()).getTimeInMillis();
        return _time > startTime && _time < endTime;
    }

    /**
     * 1天的开始时间
     */
    private static Calendar floorDay(Calendar _calendar) {
        _calendar.set(Calendar.HOUR_OF_DAY, 0);
        _calendar.set(Calendar.MINUTE, 0);
        _calendar.set(Calendar.SECOND, 0);
        _calendar.set(Calendar.MILLISECOND, 0);
        return _calendar;
    }

    /**
     * 1天的结束时间
     */
    private static Calendar ceilDay(Calendar _calendar) {
        _calendar.set(Calendar.HOUR_OF_DAY, 23);
        _calendar.set(Calendar.MINUTE, 59);
        _calendar.set(Calendar.SECOND, 59);
        _calendar.set(Calendar.MILLISECOND, 999);
        return _calendar;
    }
}
