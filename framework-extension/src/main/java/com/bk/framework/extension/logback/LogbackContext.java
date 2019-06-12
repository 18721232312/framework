package com.bk.framework.extension.logback;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author BK
 * @version V2.0
 * @description: logback上下文
 * @date 2019/5/5 11:09
 */
public class LogbackContext {

    //保存当前请求中的requestId
    private static ThreadLocal<String> requestIdThreadLocal = new ThreadLocal<>();

    /**
     * 保存唯一requestId
     */
    public static void setRequestId(String requestId) {
        if (StringUtils.isBlank(requestId)) {
            setRequestId(UUID.randomUUID().toString());
        } else {
            requestIdThreadLocal.set(requestId);
        }
    }

    public static String getRequestId() {
        return requestIdThreadLocal.get();
    }
}