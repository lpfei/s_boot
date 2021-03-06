package com.example.core.filter;

import com.example.core.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 * Created by lpfei on 2019/7/30
 */
@Slf4j
@WebFilter
@Component
public class LogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String remoteAddr = ServletUtils.getRequestIp();
        log.debug("");
        log.debug("Starting url: [{}], method: [{}], ip: [{}]", request.getRequestURL(), request.getMethod(), remoteAddr);
        // Set start time
        long startTime = System.currentTimeMillis();
        // Do filter
        filterChain.doFilter(request, response);
        log.debug("Ending   url: [{}], method: [{}], ip: [{}], status: [{}], usage: [{}] ms", request.getRequestURL(), request.getMethod(), remoteAddr, response.getStatus(), (System.currentTimeMillis() - startTime));
        log.debug("");
    }
}
