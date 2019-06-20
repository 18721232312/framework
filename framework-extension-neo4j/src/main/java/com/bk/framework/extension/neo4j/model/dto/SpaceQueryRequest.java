package com.bk.framework.extension.neo4j.model.dto;

import lombok.Data;

/**
 * @author BK
 * @version V2.0
 * @description: 空间查询请求
 * @date 2019-06-19 14:03
 */
@Data
public class SpaceQueryRequest extends BaseDto {
    /**
     * 图中节点的主键
     */
    private Long id;
    /**
     * 应用类型
     */
    private String appType;
    /**
     * 环境类型
     */
    private String envType;
}
