package com.example.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


/**
 * description:
 * Created by lpfei on 2019/7/29
 */
public class ServletUtils {

    private ServletUtils() {
    }

    /**
     * Gets current http servlet request
     *
     * @return
     */
    @NonNull
    public static Optional<HttpServletRequest> getCurrentRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(requestAttributes -> requestAttributes instanceof ServletRequestAttributes)
                .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
                .map(ServletRequestAttributes::getRequest);
    }

    /**
     * Gets request ip
     *
     * @return
     */
    @Nullable
    public static String getRequestIp() {
        return getCurrentRequest().map(CusAccessObjectUtil::getIpAddress).orElse(null);
    }

    /**
     * Gets request header
     *
     * @param header
     * @return
     */
    @Nullable
    public static String getHeaderIgnoreCase(String header) {
        return getCurrentRequest().map(request -> CusAccessObjectUtil.getHeaderIgnoreCase(request, header)).orElse(null);
    }

}
