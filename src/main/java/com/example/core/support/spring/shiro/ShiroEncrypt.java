package com.example.core.support.spring.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * description: shiro 密码加密
 * Created by lpfei on 2019/8/14
 */
public class ShiroEncrypt {


    private static final int HASHITERATIONS = 2000;
    private static final String HASHALGORITHMNAME = "MD5";

    /**
     * 项目中密码加密次数
     */
    public static int getHASHITERATIONS() {
        return HASHITERATIONS;
    }

    /**
     * 加密方式
     *
     * @return
     */
    public static String getHASHALGORITHMNAME() {
        return HASHALGORITHMNAME;
    }

    /**
     * 加密
     *
     * @param source
     * @param satl
     * @return
     */
    public static String encrypt(String source, String satl) {
        SimpleHash sh = new SimpleHash(getHASHALGORITHMNAME(), source, satl, getHASHITERATIONS());
        return sh.toString();
    }
}
