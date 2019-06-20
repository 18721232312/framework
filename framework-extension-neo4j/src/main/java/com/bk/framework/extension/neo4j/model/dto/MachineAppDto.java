package com.bk.framework.extension.neo4j.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @date 2019-06-20 00:39
 */
@Data
@Builder
public class MachineAppDto {
    /**
     * 主键
     */
    public Long id;
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
     * 版本
     */
    private String version;
    /**
     * 角色
     */
    private String role;
    /**
     * 域名
     */
    private String domain;
    /**
     * APP类型
     */
    private String appType;
}
