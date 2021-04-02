package com.demo.util.common.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author dp
 * @date 2021/3/19 3:46 下午
 */
public class RequestUtil {

    /**
     * 二进制请求体
     */
    private static final String[] BINARY_TYPES = new String[]{"image", "video", "audio"};

    /**
     * 是否请求ContentType为二进制流
     *
     * @param request
     * @return
     */
    public static boolean isBinaryContent(final HttpServletRequest request) {
        String contentType = request.getContentType();
        if (StringUtils.isBlank(contentType)) {
            return false;
        }
        for (String binaryType : BINARY_TYPES) {
            if (contentType.startsWith(binaryType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否为表单上传文件
     *
     * @param request
     * @return
     */
    public static boolean isMultipart(final HttpServletRequest request) {
        String contentType = request.getContentType();
        return StringUtils.isNotBlank(contentType) && request.getContentType().startsWith("multipart/form-data");
    }

    /**
     * 远程访问机器真实IP
     *
     * @param request
     * @return
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
