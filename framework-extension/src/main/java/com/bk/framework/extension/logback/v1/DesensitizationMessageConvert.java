package com.bk.framework.extension.logback.v1;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author BK
 * @version V2.0
 * @description: logback扩展消息转换器  支持正则脱敏
 * @date 2019/6/1 21:23
 */
@Slf4j
public class DesensitizationMessageConvert extends MessageConverter {
    private static volatile List<RuleConfig> configList;

    @Override
    public String convert(ILoggingEvent event) {
        initRuleConfig();
        return doConvert(event);
    }

    /**
     * 初始化规则配置
     */
    private void initRuleConfig() {
        if (configList == null) {
            synchronized (DesensitizationMessageConvert.class) {
                if (configList == null) {
                    configList = Lists.newArrayList();
                    Map<String, String> propertyMap = ((LoggerContext) LoggerFactory.getILoggerFactory()).getCopyOfPropertyMap();
                    for (String s : propertyMap.keySet()) {
                        if (s.startsWith("RULE_REG_")) {
                            String[] array = propertyMap.get(s).split("&");
                            if (ArrayUtils.isNotEmpty(array) && array.length == 2) {
                                configList.add(new RuleConfig(array[0], array[1]));
                            }
                        }
                    }
                    log.info("desensitization rule config init end ! ");
                }
            }
        }
    }

    /**
     * 日志内容转换
     *
     * @param event
     * @return
     */
    private String doConvert(ILoggingEvent event) {
        String result = event.getFormattedMessage();
        if (configList != null) {
            for (RuleConfig ruleConfig : configList) {
                result = ruleConfig.apply(result);
            }
        } else {
            result = super.convert(event);
        }
        return result;
    }

    @Data
    private class RuleConfig {
        private String reg;
        private String replacement;

        RuleConfig(String reg, String replacement) {
            this.reg = reg;
            this.replacement = replacement;
        }

        String apply(String message) {
            return Pattern.compile(reg).matcher(message).replaceAll(replacement);
        }
    }
}
