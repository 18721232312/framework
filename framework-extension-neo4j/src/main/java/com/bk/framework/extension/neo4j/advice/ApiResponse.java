package com.bk.framework.extension.neo4j.advice;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author BK
 * @version V2.0
 * @description: api响应结果
 * @date 2019/6/19 10:33
 */
@Builder
@Data
public class ApiResponse<T> implements Serializable {
    public int code;

    public String msg;

    public T data;
}
