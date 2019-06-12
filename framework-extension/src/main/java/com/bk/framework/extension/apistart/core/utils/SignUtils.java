package com.bk.framework.extension.apistart.core.utils;

import com.bk.framework.extension.apistart.core.common.ApiConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author BK
 * @version V2.0
 * @description: 签名工具类
 * @date 2018/9/10 10:51
 */
@Slf4j
public class SignUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static boolean verifySign(Map<String, String> signMap, String signType, String sign, String privateKey) {
        signMap.remove(ApiConstants.SIGN_KEY);
        TreeMap<String, String> treeMap = new TreeMap<>(signMap);
        Iterator it = treeMap.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            String key = (String) it.next();
            sb.append(key).append(treeMap.get(key));
        }
        String signStr = sb.toString() + privateKey;
        if (log.isDebugEnabled()) {
            log.info("signStr = : " + signStr);
        }
        String newSign;
        try {
            newSign = getSign(signStr, signType, treeMap.get(ApiConstants.CHARSET_KEY));
            //            if(log.isDebugEnabled()) {
            //            log.info("signMap : {} , signType : {} , privateKey : {} , sign : {}", signMap, signType, privateKey, newSign);
            //            }
        } catch (Exception e) {
            log.info("generate newSign hasError", e);
            return false;
        }
        return sign.equals(newSign);
    }

    public static String getSign(String signStr, String signType, String charset) throws Exception {
        if (CoderUtil.KEY_MD5.equals(signType)) {
            return convertToString(CoderUtil.encryptMD5(signStr, charset));
        }
        if (CoderUtil.KEY_SHA.equals(signType)) {
            return convertToString(CoderUtil.encryptSHA(signStr, charset));
        }
        return convertToString(CoderUtil.encryptMD5(CoderUtil.KEY_MD5, charset));
    }

    private static String convertToString(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (byte b : bytes) {
            //将没个数(int)b进行双字节加密
            buf.append(HEX_DIGITS[(b >>> 4) & 0x0f]);//高4位
            buf.append(HEX_DIGITS[b & 0x0f]);//低4位
        }
        return buf.toString();
    }

}
