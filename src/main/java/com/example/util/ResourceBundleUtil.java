package com.example.util;

import java.util.ResourceBundle;

/**
 * @Author: lpfei.
 * @Description: 读取properties
 * @Date:Created in 2017/9/13.
 * @Modified By:
 */
public class ResourceBundleUtil {

    static String config = "application.properties";

    public static final ResourceBundle bundle = ResourceBundle.getBundle(config);
}
