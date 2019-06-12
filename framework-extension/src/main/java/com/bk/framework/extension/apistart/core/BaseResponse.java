package com.bk.framework.extension.apistart.core;

import lombok.Data;

/**
 * @author BK
 * @version V2.0
 * @description: 基础响应类
 * @date 2018/9/7 10:39
 */
@Data
public class BaseResponse {
    /**
     * 调用结果 0成功 1失败
     */
    protected String result = "";

    /**
     * 错误代码
     */
    protected String errorCode = "";
    /**
     * 错误信息
     */
    protected String msg = "";

    public BaseResponse() {
    }

    public BaseResponse(String result) {
        this.result = result;
    }

    public BaseResponse(String result, String errorCode, String msg) {
        this.result = result;
        this.errorCode = errorCode;
        this.msg = msg;
    }
}
