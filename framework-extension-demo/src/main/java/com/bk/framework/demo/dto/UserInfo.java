package com.bk.framework.demo.dto;

import com.bk.framework.extension.logback.annotation.Desensitization;
import lombok.Data;

/**
 * @author BK
 * @version V2.0
 * @description:  demo dto
 * @date 2019-06-12 23:29
 */
@Data
public class UserInfo {
    @Desensitization(type = "NAME")
    private String userName;
    @Desensitization(type = "MOBILE")
    private String mobile;
    @Desensitization(type = "EMAIL")
    private String email ;
    private String sex ;
}
