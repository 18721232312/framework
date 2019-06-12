package com.bk.framework.extension.apistart.web;

import com.alibaba.fastjson.JSONObject;
import com.bk.framework.extension.apistart.core.BaseRequest;
import com.bk.framework.extension.apistart.core.BaseResponse;
import com.bk.framework.extension.apistart.core.common.ErrorCode;
import com.bk.framework.extension.apistart.core.process.AbstractRequestProcessor;
import com.bk.framework.extension.apistart.core.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @date 2019/5/20 15:15
 */
@Slf4j
@RequestMapping("/api")
@RestController
public class AccessController {

    @Autowired
    private AbstractRequestProcessor abstractRequestProcessor;


    @RequestMapping("request")
    public BaseResponse request(@RequestBody String json) {
        log.info("/api/json post received requestBody : {} ", json);
        BaseRequest baseRequest = null;
        BaseResponse response;
        try {
            baseRequest = JSONObject.parseObject(json, BaseRequest.class);
            log.info(baseRequest.getRequestId() + "/api/request post received requestBody : {} ", JSONObject.toJSONString(baseRequest));
            response = abstractRequestProcessor.process(baseRequest);
        } catch (Exception e) {
            response = ResponseUtils.buildFailResponse(ErrorCode.PARAMETER_INVALID.getCode(), "request body parse error !");
        }
        log.info(baseRequest.getRequestId() + "/api/request post received responseBody : {} ", JSONObject.toJSONString(response));
        return response;
    }

}
