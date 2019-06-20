package com.bk.framework.extension.neo4j.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @date 2019-06-19 15:56
 */
@Data
@Builder
public class SpaceDto {
    /**
     * 主键
     */
    private Long id;
    /**
     * title
     */
    public String title;
    /**
     * 编码
     */
    public String code;
    /**
     * 类型
     */
    public String type;
    /**
     * accessKey
     */
    private String accessKey;
    /**
     * 描述
     */
    private String description;
    /**
     * 项目编码
     */
    private String projectCode;
}
