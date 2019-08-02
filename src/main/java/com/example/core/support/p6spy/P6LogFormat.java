package com.example.core.support.p6spy;

import com.p6spy.engine.spy.appender.SingleLineFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:
 * Created by lpfei on 2019/8/2
 */
public class P6LogFormat extends SingleLineFormat {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        return !"".equals(sql.trim()) ? this.format.format(new Date()) + " | took " + elapsed + "ms | " + category + " | connection " + connectionId + "\n " + sql + ";" : "";
    }

}
