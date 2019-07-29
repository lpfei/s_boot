package com.example.util.date;

import jodd.datetime.JDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * jDateTime 工具类
 * Created by lpfei on 2017/5/3.
 */
public class JdateUtil {

    private static final String FORMAT_YMDHMS = "YYYY-MM-DD hh:mm:ss";
    private static final String FORMAT_YMD = "YYYY-MM-DD";
    private static Logger logger = LoggerFactory.getLogger(JdateUtil.class);

    /**
     * 获取当前时间
     *
     * @return JDateTime
     */
    public static JDateTime getCurrJDate() {
        return new JDateTime(new Date());
    }

    /**
     * 获取当前时间 YMDHMS
     *
     * @return Date
     */
    public static Date getCurrDate() {
        return new JDateTime(getCurrDateStr()).convertToDate();
    }

    /**
     * 获取当前时间字符串 YMDHMS
     *
     * @return String
     */
    public static String getCurrDateStr() {
        return getCurrDateStr(FORMAT_YMDHMS);
    }

    /**
     * 获取当前时间字符串
     *
     * @param format
     * @return String
     */
    public static String getCurrDateStr(String format) {
        return getCurrJDate().toString(format);
    }

    public static void main(String[] args) {
        JdateUtil util = new JdateUtil();
        logger.debug("当前时间:{}", util.getCurrDateStr());
    }
}
