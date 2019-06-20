package com.bk.framework.extension.neo4j.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author BK
 * @version V2.0
 * @description: 服务器DTO
 * @date 2019-06-20 01:23
 */
@Data
@Builder
public class MachineDto {
    /**
     * 主键
     */
    protected Long id;

    /**
     * title
     */
    protected String title;
    /**
     * 编码
     */
    protected String code;
    /**
     * 类型
     */
    protected String type;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 状态
     */
    private String status;
    /**
     * 状态
     */
    private String system;
}
