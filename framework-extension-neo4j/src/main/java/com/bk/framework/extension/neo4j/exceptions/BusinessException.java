package com.bk.framework.extension.neo4j.exceptions;

/**
 * @author BK
 * @version V2.0
 * @description: 业务运行时异常
 * @date 2019-06-19 13:50
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
