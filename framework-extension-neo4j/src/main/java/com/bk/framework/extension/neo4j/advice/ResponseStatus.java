package com.bk.framework.extension.neo4j.advice;

/**
 * @author BK
 * @version V2.0
 * @description: 响应状态枚举
 * @date 2019-06-19 13:49
 */
public enum ResponseStatus {

    FAIL_STATUS(0, "请求成功"), SUCCESS_STATUS(1, "请求失败");
    private int key;
    private String value;

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    ResponseStatus(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
