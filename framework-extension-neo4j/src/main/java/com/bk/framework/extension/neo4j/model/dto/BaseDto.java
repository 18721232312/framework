package com.bk.framework.extension.neo4j.model.dto;

import lombok.Data;

/**
 * @author BK
 * @version V2.0
 * @description: 基础DTO
 * @date 2019-06-19 14:04
 */
@Data
public class BaseDto {
    /**
     * 接入KEY
     */
    private String accessKey;
    /**
     * 项目编码
     */
    private String projectCode;
}
