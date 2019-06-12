package com.bk.framework.extension.apistart.core.utils;

import com.bk.framework.extension.apistart.core.BaseResponse;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @date 2019/5/20 15:53
 */
public class ResponseUtils {

    public static final String SUCCESS = "0";
    public static final String FAIL = "1";

    public static BaseResponse buildSuccess() {

        return new BaseResponse(SUCCESS);
    }

    public static BaseResponse buildFailResponse(String errorCode, String errorMessage) {
        return new BaseResponse(FAIL, errorCode, errorMessage);
    }

}
