package com.bk.framework.extension.apistart.core.utils;

import java.security.MessageDigest;

/**
 * @author BK
 * @version V2.0
 * @description: 加密工具类
 * @date 2018/9/10 15:37
 */
public class CoderUtil {
    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";

    public CoderUtil() {
    }

    public static byte[] encryptMD5(String data, String charset) throws Exception {
        return doEncrypt(KEY_MD5, data, charset);
    }

    public static byte[] encryptSHA(String data, String charset) throws Exception {
        return doEncrypt(KEY_SHA, data, charset);
    }

    private static byte[] doEncrypt(String type, String data, String charset) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(type);
        md5.update(data.getBytes(charset));
        return md5.digest();
    }
}
