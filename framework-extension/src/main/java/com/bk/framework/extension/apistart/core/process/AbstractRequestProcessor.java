package com.bk.framework.extension.apistart.core.process;

import com.alibaba.fastjson.JSONObject;
import com.bk.framework.extension.apistart.core.BaseRequest;
import com.bk.framework.extension.apistart.core.BaseResponse;
import com.bk.framework.extension.apistart.core.common.ApiConstants;
import com.bk.framework.extension.apistart.core.common.ErrorCode;
import com.bk.framework.extension.apistart.core.utils.BeanUtils;
import com.bk.framework.extension.apistart.core.utils.ResponseUtils;
import com.bk.framework.extension.apistart.core.utils.SignUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author BK
 * @version V2.0
 * @description: 抽象处理器
 * @date 2019/5/20 15:31
 */
@Slf4j
public abstract class AbstractRequestProcessor<R extends BaseRequest> {

    private static final String SKIP_SIGN = "1";

    /**
     * 处理sdk请求
     *
     * @param request 请求参数
     * @return
     */
    public BaseResponse process(R request) {
        log.info("Processor received request : {}", JSONObject.toJSONString(request));
        BaseResponse response = check(request);
        if (ResponseUtils.FAIL.equals(response.getResult())) {
            return response;
        }
        response = doProcess(request);
        log.info("Processor response : {}", JSONObject.toJSONString(response));
        return response;
    }

    /**
     * 抽象处理过程
     *
     * @param request request
     * @return
     */
    protected abstract BaseResponse doProcess(R request);

    /**
     * 检查请求参数合法性
     *
     * @param request 请求参数
     * @return
     */
    protected BaseResponse check(R request) {
        BaseResponse response = request.check();
        if (ResponseUtils.FAIL.equals(response.getResult())) {
            return response;
        }
        TreeMap<String, String> sortMap = Maps.newTreeMap();
        sortMap.putAll(BeanUtils.beanToMap(request));
        Map<String, String> clientInfo = getClientInfo(request);
        if (clientInfo != null && (StringUtils.isBlank(clientInfo.get(ApiConstants.SKIP_SIGN)) || SKIP_SIGN.equals(clientInfo.get(ApiConstants.SKIP_SIGN)))) {
            boolean verifySignResult = SignUtils.verifySign(sortMap, request.getSignType(), request.getSign(), clientInfo.get(ApiConstants.SECRET_KEY));
            if (!verifySignResult) {
                return ResponseUtils.buildFailResponse(ErrorCode.SIGN_ERROR.getCode(), ErrorCode.SIGN_ERROR.getDesc());
            }
        }
        return doCheck(request);
    }

    /**
     * 获取客户端信息
     * skipSign  是否跳过签名  空、0不跳过  1跳过
     * secretKey 密钥  客户端的密钥
     *
     * @param request baseRequest
     * @return
     */
    protected abstract Map<String, String> getClientInfo(R request);

    /**
     * 检查请求参数合法性
     *
     * @param request 请求参数
     * @return
     */
    protected abstract BaseResponse doCheck(R request);
}

