package com.example.core.support.redis;

import org.springframework.context.annotation.Bean;

/**
 * spring session支持
 * Created by lpfei on 2017/3/30.
 */
//@Configuration
//@EnableRedisHttpSession
public class RedisSessionConfig {
    /**
     * todo:session 共享cookie 保存目录设置,用于解决session
     *
     * @return
     */
    /*@Bean
    public CookieSerializer cookieSerializer() {
        // 默认 Cookie 序列化
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        // Cookie名字，默认为 SESSION
//        defaultCookieSerializer.setCookieName("SESSION_ID");
        // 域，这允许跨子域共享cookie，默认设置是使用当前域。
//        defaultCookieSerializer.setDomainName("xuxiaowei.com.cn");
        // Cookie的路径。
        defaultCookieSerializer.setCookiePath("/admin/");
        return defaultCookieSerializer;
    }*/
}
