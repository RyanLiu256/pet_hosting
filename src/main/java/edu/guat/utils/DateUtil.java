package edu.guat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 将指定的日期字符串转换成日期
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return 日期对象
     */
    public static Date parseDate(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("日期转化错误");
        }

        return date;
    }

    /**
     * 将指定的日期格式化成指定的日期字符串
     *
     * @param date    日期对象
     * @param pattern 格式
     * @return 格式化后的日期字符串
     */
    public static String dateFormate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateStr;
        if (date == null) {
            return "";
        }
        dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 查询指定日期前后指定的天数
     *
     * @param date 日期对象
     * @param days 天数
     * @return 日期对象
     */
    public static Date incr(Date date, int days) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 将LocalDate日期转化成Date
     *
     * @param localDate LocalDate对象
     * @return Date对象
     */
    public static Date localDateToDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zonedDateTime.toInstant());

        return date;
    }

    /**
     * 将Date转成LocalDate对象
     *
     * @param date Date对象
     * @return LocalDate对象
     */
    public static LocalDate dateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();

        return localDate;
    }

}
