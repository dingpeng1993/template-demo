package com.demo.util.common.log;

import com.demo.util.common.util.ContentCachingWrapperUtil;
import com.demo.util.common.util.JsonHelper;
import com.demo.util.common.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import java.util.Objects;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dp
 * @date 2021/3/19 3:41 下午
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 访问时间戳
     */
    public static final String ACCESS_START_TIMESTAMP = "__access_start_timestamp";

    @Pointcut("@annotation(com.demo.util.common.log.LogPoint)")
    public void pointCut(){}

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 获取httpServletRequest
        HttpServletRequest request = attributes.getRequest();
        request.setAttribute(ACCESS_START_TIMESTAMP, System.currentTimeMillis());
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void doAfter(JoinPoint joinPoint, Object result) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 获取httpServletRequest
        HttpServletRequest request = attributes.getRequest();

        String uri = request.getRequestURI();
        String method = request.getMethod();
        String param = StringUtils.EMPTY;
        if (request instanceof ContentCachingRequestWrapper) {
            param = ContentCachingWrapperUtil.getParam((ContentCachingRequestWrapper) request);
        }
        String response = Objects.isNull(result) ? StringUtils.EMPTY : JsonHelper.toJson(result);
        String ip = RequestUtil.getRequestIp(request);
        Long startTimestamp = (Long) request.getAttribute(ACCESS_START_TIMESTAMP);
        log(ip, uri, method, param, response, startTimestamp);
    }

    private void log(String ip, String url, String method, String param, String response, Long startTimeStamp) {
        LogInfo logInfo = new LogInfo();
        logInfo.append("ip", ip)
                .append("url", url)
                .append("method", method)
                .append("param", param)
                .append("resp", response)
                .append("proc_time", System.currentTimeMillis() - startTimeStamp);
        //log.info(logInfo.toString());
    }

}
