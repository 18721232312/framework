package com.bk.framework.extension.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author BK
 * @version V2.0
 * @description: 日志中增加请求ID信息
 * @date 2019/5/5 11:09
 */
public class ClassicConverterExt extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        if (StringUtils.isBlank(LogbackContext.getRequestId())) {
            LogbackContext.setRequestId(UUID.randomUUID().toString().replace("-", ""));
        }
        return LogbackContext.getRequestId();
    }
}