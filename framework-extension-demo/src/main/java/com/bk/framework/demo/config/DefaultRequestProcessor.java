package com.bk.framework.demo.config;

import com.bk.framework.extension.apistart.core.BaseRequest;
import com.bk.framework.extension.apistart.core.BaseResponse;
import com.bk.framework.extension.apistart.core.common.ApiConstants;
import com.bk.framework.extension.apistart.core.process.AbstractRequestProcessor;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BK
 * @version V2.0
 * @description: 默认的请求处理器
 * @date 2019-06-13 00:11
 */
@Component
public class DefaultRequestProcessor extends AbstractRequestProcessor {
    @Override
    protected BaseResponse doProcess(BaseRequest request) {
        BaseResponse res = new BaseResponse();
        res.setResult("0");
        return res;
    }

    @Override
    protected Map<String, String> getClientInfo(BaseRequest request) {
        //TODO 添加自己获取客户信息逻辑在这里 根据request里面的clientCode获取它的密钥和是否需要签名的配置
        HashMap<String, String> res = Maps.newHashMap();
        res.put(ApiConstants.SKIP_SIGN, "0");//不校验签名
        return res;
    }

    @Override
    protected BaseResponse doCheck(BaseRequest request) {
        return new BaseResponse();
    }
}
