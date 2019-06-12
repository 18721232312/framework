package com.bk.framework.extension.apistart.core.common;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @date 2019/5/20 17:47
 */
public enum ErrorCode {
    NO_ACCESS("E0001", "客户未接入"), NOT_AVAILABLE_BUSINESS("E0002", "客户端未开通此业务"), SIGN_ERROR("E0003", "签名失败"), PARAMETER_INVALID("E0007", "参数校验错误"), OVER_FREQUENCY("E0008", "提交的频率太快,休息下先喝杯咖啡!"), ORDER_ALREADY_EXISTS("E0009", "订单已存在,请勿重复提交!"), CLIENT_CODE_NOT_EXIST("E0011", "客户编号不存在！"), OTHER_ERROR("E9999", "其它错误");
    private String code;
    private String desc;

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String transferToCode(String desc) {
        for (ErrorCode value : ErrorCode.values()) {
            if (value.getDesc().equals(desc)) {
                return value.getCode();
            }
        }
        return ErrorCode.OTHER_ERROR.getCode();
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
