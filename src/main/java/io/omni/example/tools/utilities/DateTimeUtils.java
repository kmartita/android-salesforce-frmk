package io.omni.example.tools.utilities;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DateTimeUtils {

    public static final String US_LOCALE_DATE_TIME_FORMAT = "M/d/yyyy h:mm a";
    public static final String US_LOCALE_DATE_FORMAT = "M/d/yyyy";
    public static final String DB_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS+SSSS";
    public static final String TIME_PATTERN = "hh:mm";
    public static final String DB_DATE_FORMAT = "yyyy-MM-dd";

    public static LocalDateTime getNearestDateTime(LocalDateTime dateTime) {
        LocalTime roundedTime = getNearestTime(LocalTime.of(dateTime.getHour(), dateTime.getMinute()));
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(),
                dateTime.getDayOfMonth(), roundedTime.getHour(), roundedTime.getMinute(), dateTime.getSecond(), dateTime.getNano());
    }

    public static LocalTime getNearestTime(LocalTime lTime) {
        int minute = lTime.getMinute();
        int hour = lTime.getHour();
        int resultMinute;
        if (minute > 0 && minute < 30) {
            resultMinute = 0;
        } else resultMinute = Math.min(minute, 30);
        return LocalTime.of(hour, resultMinute);
    }

    public static String getGridHourValueForPlanner(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("h:00 a"));
    }

    public static String getGridDayValueForPlanner(LocalDateTime day) {
        return day.format(DateTimeFormatter.ofPattern("LLL d"));
    }

    public static String getHeaderDayValueForPlanner(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("EEE MMMM d")).replaceFirst(StringUtils.SPACE, "\n");
    }

    public static String getAllDayValueForPlanner(LocalDateTime day) {
        return day.format(DateTimeFormatter.ofPattern("E LLLL d")).replaceFirst(StringUtils.SPACE, "\n");
    }

    public static LocalDate convertStringToLocalDate(String date, String pattern) {
        DateTimeFormatter formatter = ofPattern(pattern);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDateTime convertStringToLocalDateTime(String date, String pattern) {
        DateTimeFormatter formatter = ofPattern(pattern);
        return LocalDateTime.parse(date, formatter);
    }

    public static String formatDateByPattern(LocalDateTime date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String convertLocalDateToCivilianDateFormat(LocalDate date) {
        return date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear();
    }
}
