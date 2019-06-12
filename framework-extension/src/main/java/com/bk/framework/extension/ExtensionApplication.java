package com.bk.framework.extension;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BK
 * @version V2.0
 * @date 2019-06-03 23:43
 */
@Slf4j
public class ExtensionApplication {

    public static void main(String[] args) {
        log.info("mobile:{}", "13588889999");
        log.info("userInfo:{}", "{\n" + "            \"userName\":\"罗志祥\"，\n" + "            \"idcard\":\"321183197701017846\",\n" + "            \"password\":\"luozhixiang1234\",\n" + "            \"mobile\":\"18888888888\"\n" + "        }");
    }
}
