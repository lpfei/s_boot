package com.example.core.filter;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.example.core.enums.DatePattern;
import com.example.core.util.date.JdateUtil;

import java.util.Date;

/**
 * description:fastjson 自定义数据格式化filter
 * Created by lpfei on 2019/7/31
 */
public class ValueFormatFilter implements ValueFilter {
    @Override
    public Object process(Object object, String name, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Date) {
            return JdateUtil.dateFormat((Date) value, DatePattern.YMDHMS.getValue());
        }
        return value;
    }
}
