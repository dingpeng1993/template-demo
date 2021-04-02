package com.demo.util.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Optional;

/**
 * @author dp
 * @date 2021/3/19 3:48 下午
 */
@Slf4j
public class ContentCachingWrapperUtil {

    public static final String HTTP_METHOD_GET = "GET";

    public static final String APPLICATION_JSON = "application/json";

    /**
     * @param requestWrapper
     * @return
     */
    public static String getParam(ContentCachingRequestWrapper requestWrapper) {
        try {
            String method = requestWrapper.getMethod();
            if (RequestUtil.isBinaryContent(requestWrapper) || RequestUtil.isMultipart(requestWrapper)) {
                return StringUtils.EMPTY;
            }
            String queryString = requestWrapper.getQueryString();
            if (HTTP_METHOD_GET.equals(method)) {
                return Optional.ofNullable(queryString).orElse("");
            } else {
                String body = IOUtils.toString(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
                return Optional.ofNullable(body).orElse(Optional.ofNullable(queryString).orElse("")).replace("\n","");
            }
        } catch (IOException e) {
            return StringUtils.EMPTY;
        }
    }
}
