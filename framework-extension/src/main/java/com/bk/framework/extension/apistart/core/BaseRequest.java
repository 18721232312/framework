package com.bk.framework.extension.apistart.core;

import com.bk.framework.extension.apistart.core.common.ApiConstants;
import com.bk.framework.extension.apistart.core.common.ErrorCode;
import com.bk.framework.extension.apistart.core.utils.ResponseUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author BK
 * @version V2.0
 * @description: 请求基础类
 * @date 2018/9/10 10:39
 */
@Data
public class BaseRequest implements Serializable {
    /**
     * 客户端编码
     */
    protected String clientCode;
    /**
     * 客户端字符集
     */
    protected String charset = ApiConstants.CHAT_SET_UTF8;
    /**
     * 请求ID
     */
    protected String requestId;
    /**
     * 请求时间
     */
    protected String requestTime;
    /**
     * 签名方式
     */
    protected String signType;
    /**
     * 签名
     */
    protected String sign;
    /**
     * 请求方法
     */
    protected String method;
    /**
     * 请求体
     */
    protected String requestBody;

    /**
     * 参数校验
     *
     * @return false 不通过  true 通过
     */
    public BaseResponse check() {
        if (StringUtils.isBlank(clientCode)) {
            return ResponseUtils.buildFailResponse(ErrorCode.PARAMETER_INVALID.getCode(), "clientCode is blank");
        }
        if (StringUtils.isBlank(requestId)) {
            return ResponseUtils.buildFailResponse(ErrorCode.PARAMETER_INVALID.getCode(), "requestId is blank");
        }
        try {
            Long.valueOf(requestTime);
        } catch (NumberFormatException e) {
            return ResponseUtils.buildFailResponse(ErrorCode.PARAMETER_INVALID.getCode(), "requestTime is invalid");
        }
        if (StringUtils.isBlank(signType)) {
            return ResponseUtils.buildFailResponse(ErrorCode.PARAMETER_INVALID.getCode(), "signType is blank");
        }
        if (StringUtils.isBlank(method)) {
            return ResponseUtils.buildFailResponse(ErrorCode.PARAMETER_INVALID.getCode(), "method is blank");
        }
        if (StringUtils.isBlank(sign)) {
            return ResponseUtils.buildFailResponse(ErrorCode.PARAMETER_INVALID.getCode(), "sign is blank");
        }
        if (StringUtils.isBlank(requestBody)) {
            return ResponseUtils.buildFailResponse(ErrorCode.PARAMETER_INVALID.getCode(), "requestBody is blank");
        }
        return ResponseUtils.buildSuccess();
    }
}
