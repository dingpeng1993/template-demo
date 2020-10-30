package com.demo.util.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * @author dp
 * @date 2020/3/15 1:59 下午
 */
public class DateUtil {

    /**
     * 日期 24小时制
     */
    public static final String DEFAULT_FORMAT_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 秒
     */
    public static final String DEFAULT_FORMAT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private DateUtil() {
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {

        private static TemporalField ISO_WEEKFIELDS = WeekFields.ISO.dayOfWeek();

        private LocalDateTime dateTime;

        private Builder withCurrentDate() {
            dateTime = LocalDateTime.now();
            return this;
        }

        /**
         * 按照指定日期
         *
         * @param date 指定日期
         * @return Builder
         */
        public Builder withDate(Date date) {
            Instant instant = date.toInstant();
            dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            return this;
        }

        /**
         * 按照毫秒数置顶日期
         *
         * @param millSeconds 毫秒数
         * @return Builder
         */
        public Builder withMillSeconds(long millSeconds) {
            Instant instant = Instant.ofEpochMilli(millSeconds);
            dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            return this;
        }

        /**
         * 按照秒数日期指定日期
         *
         * @param seconds 秒
         * @return Builder
         */
        public Builder withSeconds(long seconds) {
            Instant instant = Instant.ofEpochSecond(seconds);
            dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            return this;
        }

        /**
         * 按照字符串日期 YYYY-MM-DD
         *
         * @param dataStr 格式化的日期
         * @return Builder
         */
        public Builder parseDate(String dataStr) {
            return parse(dataStr, DEFAULT_FORMAT_DATE_PATTERN);
        }

        /**
         * 按照字符串日期 yyyy-MM-dd HH:mm:ss
         *
         * @param dataStr 格式化的日期
         * @return Builder
         */
        public Builder parseDateTime(String dataStr) {
            return parse(dataStr, DEFAULT_FORMAT_DATETIME_PATTERN);
        }

        /**
         * 按照字符串日期
         *
         * @param dataStr 格式化的日期
         * @param pattern 格式化的形式
         * @return Builder
         */
        public Builder parse(String dataStr, String pattern) {
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern(pattern);
            DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().append(df1)
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                    .toFormatter();
            dateTime = LocalDateTime.parse(dataStr, dateTimeFormatter);
            return this;
        }

        /**
         * 指定年份
         *
         * @param year 年
         * @return Builder
         */
        public Builder withYear(int year) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withYear(year);
            return this;
        }

        /**
         * 指定月份
         *
         * @param month 月份
         * @return Builder
         */
        public Builder withMonth(int month) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withMonth(month);
            return this;
        }

        /**
         * 指定一年中的周数
         *
         * @param week 星期
         * @return Builder
         */
        public Builder withWeekOfYear(int week) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            dateTime = dateTime.with(weekFields.weekOfYear(), week);
            return this;
        }

        /**
         * 指定月份中的日期
         *
         * @param dayOfMonth 一个月中的天
         * @return Builder
         */
        public Builder withDayOfMonth(int dayOfMonth) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withDayOfMonth(dayOfMonth);
            return this;
        }

        /**
         * 指定一天中的小时
         *
         * @param hourOfDay 一天中的小时
         * @return Builder
         */
        public Builder withHourOfDay(int hourOfDay) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withHour(hourOfDay);
            return this;
        }

        /**
         * 指定小时中的分钟
         *
         * @param minuteOfHour 小时中的分钟
         * @return Builder
         */
        public Builder withMinuteOfHour(int minuteOfHour) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withMinute(minuteOfHour);
            return this;
        }

        /**
         * 指定分钟的秒数
         *
         * @param secondOfMinute 分钟的秒数
         * @return 分钟的秒数
         */
        public Builder withSecondOfMinute(int secondOfMinute) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withSecond(secondOfMinute);
            return this;
        }

        /**
         * 指定毫秒数
         *
         * @param millisOfSecond 毫秒数
         * @return Builder
         */
        public Builder withMillisOfSecond(int millisOfSecond) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(ChronoField.MILLI_OF_SECOND, millisOfSecond);
            return this;
        }

        /**
         * 指定年月日时分秒毫秒
         *
         * @param year 年
         * @param monthOfYear 月
         * @param dayOfMonth 日
         * @param hourOfDay 小时
         * @param minuteOfHour 分钟
         * @param secondOfMinute 秒
         * @param milliOfSecond 毫秒
         * @return Builder
         */
        public Builder with(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int milliOfSecond) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withYear(year)
                    .withMonth(monthOfYear)
                    .withDayOfMonth(dayOfMonth)
                    .withHour(hourOfDay)
                    .withMinute(minuteOfHour)
                    .withSecond(secondOfMinute)
                    .with(ChronoField.MILLI_OF_SECOND, milliOfSecond);
            return this;
        }

        /**
         * 指定年月日
         *
         * @param year 年
         * @param monthOfYear 月
         * @param dayOfMonth 日
         * @return Builder
         */
        public Builder with(int year, int monthOfYear, int dayOfMonth) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.withYear(year)
                    .withMonth(monthOfYear)
                    .withDayOfMonth(dayOfMonth);
            return this;
        }

        /**
         * 新增年份
         *
         * @param years 增加的年份
         * @return Builder
         */
        public Builder plusYears(int years) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            if (years == 0) {
                return this;
            }
            dateTime = dateTime.plusYears(years);
            return this;
        }


        /**
         * 增加月份
         *
         * @param months 增加的月份
         * @return Builder
         */
        public Builder plusMonths(int months) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            if (months == 0) {
                return this;
            }
            dateTime = dateTime.plusMonths(months);
            return this;
        }


        /**
         * 增加周数
         *
         * @param weeks 增加的星期
         * @return Builder
         */
        public Builder plusWeeks(int weeks) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            if (weeks == 0) {
                return this;
            }
            dateTime = dateTime.plusWeeks(weeks);
            return this;
        }

        /**
         * 增加天数
         *
         * @param days 增加的天数
         * @return Builder
         */
        public Builder plusDays(int days) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            if (days == 0) {
                return this;
            }
            dateTime = dateTime.plusDays(days);
            return this;
        }

        /**
         * 增加小时
         *
         * @param hours 增加的小时
         * @return Builder
         */
        public Builder plusHours(int hours) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            if (hours == 0) {
                return this;
            }
            dateTime = dateTime.plusHours(hours);
            return this;
        }

        /**
         * 增加分钟数
         *
         * @param minutes 增加的分钟数
         * @return Builder
         */
        public Builder plusMinutes(int minutes) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            if (minutes == 0) {
                return this;
            }
            dateTime = dateTime.plusMinutes(minutes);
            return this;
        }

        /**
         * 增加秒数
         *
         * @param seconds 增加的秒数
         * @return Builder
         */
        public Builder plusSeconds(int seconds) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            if (seconds == 0) {
                return this;
            }
            dateTime = dateTime.plusSeconds(seconds);
            return this;
        }

        /**
         * 得到当月的第一天
         *
         * @return Builder
         */
        public Builder withFirstDayOfMonth() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(TemporalAdjusters.firstDayOfMonth());
            return this;
        }

        /**
         * 得到当月的最后一天
         *
         * @return Builder
         */
        public Builder withLastDayOfMonth() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(TemporalAdjusters.lastDayOfMonth());
            return this;
        }

        /**
         * 得到一周的第一天 周一为第一天
         *
         * @return Builder
         */
        public Builder withFirstDayOfWeek() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(ISO_WEEKFIELDS, 1);
            return this;
        }

        /**
         * 一周的最后一天 周日为第七天
         *
         * @return Builder
         */
        public Builder withLastDayOfWeek() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(ISO_WEEKFIELDS, 7);
            return this;
        }


        /**
         * 一天的最后一毫秒
         *
         * @return builder
         */
        public Builder withLastMillsOfDay() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(LocalTime.MAX);
            return this;
        }

        /**
         * 一天的第0秒
         *
         * @return Builder
         */
        public Builder withFirstMillsOfDay() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            dateTime = dateTime.with(LocalTime.MIN);
            return this;
        }

        /**
         * 获取年分
         *
         * @return 年份
         */
        public int getYear() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getYear();
        }

        /**
         * 获取月份
         *
         * @return 月份
         */
        public int getMonth() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getMonthValue();
        }

        /**
         * 获取日期在一年中的第几天
         *
         * @return 一年中的第几天
         */
        public int getDayOfYear() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getDayOfYear();
        }

        /**
         * 获取日期在一个月中的第几天
         *
         * @return 一个月中的第几天
         */
        public int getDayOfMonth() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getDayOfMonth();
        }

        /**
         * 获取日期是一周中的第几天
         *
         * @return 一周中的第几天
         */
        public int getDayOfWeek() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.get(ISO_WEEKFIELDS);
        }

        /**
         * 获取日期中的小时部分值
         *
         * @return 小时部分值
         */
        public int getHourOfDay() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getHour();
        }

        /**
         * 获取日期对象中的分钟部分值
         *
         * @return 分钟部分值
         */
        public int getMinuteOfHour() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getMinute();
        }

        /**
         * 获取日期对象的秒部分值
         *
         * @return 毫秒部分值
         */
        public int getSecondOfMinute() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.getSecond();
        }

        /**
         * 获取日期对象的毫秒部分值
         *
         * @return 秒部分值
         */
        public int getMillsOfSecond() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.get(ChronoField.MILLI_OF_SECOND);
        }

        /**
         * 获取当前日期
         *
         * @return 当前日期的Date对象
         */
        public Date getDate() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        }

        /**
         * 获取日期对应毫秒数
         *
         * @return 毫秒
         */
        public long getMillis() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

        /**
         * 获取日期对应秒数
         *
         * @return 秒
         */
        public long getSeconds() {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            return dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
        }

        /**
         * 格式化为 YYYY-MM-DD
         *
         * @return 格式化的时间
         */
        public String formatDate() {
            return format(DEFAULT_FORMAT_DATE_PATTERN);
        }

        /**
         * 格式化为 yyyy-MM-dd HH:mm:ss
         *
         * @return 格式化的时间
         */
        public String formatDateTime() {
            return format(DEFAULT_FORMAT_DATETIME_PATTERN);
        }

        /**
         * 按照指定日期格式化
         *
         * @param datePattern 时间格式
         * @return 格式化的时间
         */
        public String format(String datePattern) {
            if (Objects.isNull(dateTime)) {
                withCurrentDate();
            }
            Objects.requireNonNull(datePattern, "date pattern must not null");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern, Locale.getDefault());
            return ZonedDateTime.of(dateTime, ZoneId.systemDefault()).format(dateTimeFormatter);
        }

    }


}
