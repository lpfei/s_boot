package com.example.core.util.date;

import com.example.core.enums.DatePattern;
import jodd.datetime.JDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * jDateTime 工具类
 * Created by lpfei on 2017/5/3.
 */
public class JdateUtil {

    /**
     * 获取当前时间
     *
     * @return JDateTime
     */
    public static JDateTime currJDate() {
        return new JDateTime(new Date());
    }

    /**
     * 获取当前时间 YMDHMS
     *
     * @return Date
     */
    public static Date currDate() {
        return new JDateTime(currDateStr()).convertToDate();
    }

    /**
     * 获取当前时间字符串 YMDHMS
     *
     * @return String
     */
    public static String currDateStr() {
        return currDateStr(DatePattern.YMDHMS.getValue());
    }

    /**
     * 获取当前时间字符串
     *
     * @param format
     * @return String
     */
    public static String currDateStr(String format) {
        return currJDate().toString(format);
    }

    /**
     * 格式化时间
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        return new JDateTime(date).toString(pattern);
    }

}
