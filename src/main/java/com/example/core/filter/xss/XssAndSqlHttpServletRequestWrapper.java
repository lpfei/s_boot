package com.example.core.filter.xss;


import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * description:防止xss攻击,sql注入
 * Created by lpfei on 2019/8/1
 */
public class XssAndSqlHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private HttpServletRequest request;

    public XssAndSqlHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if (!StringUtils.isEmpty(value)) {
            value = org.apache.commons.text.StringEscapeUtils.escapeHtml4(value);
            value = StringEscapeUtils.escapeSql(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (parameterValues == null) {
            return null;
        }
        for (int i = 0; i < parameterValues.length; i++) {
            String value = parameterValues[i];
            parameterValues[i] = org.apache.commons.text.StringEscapeUtils.escapeHtml4(value);
            parameterValues[i] = StringEscapeUtils.escapeSql(value);
        }
        return parameterValues;
    }
}
