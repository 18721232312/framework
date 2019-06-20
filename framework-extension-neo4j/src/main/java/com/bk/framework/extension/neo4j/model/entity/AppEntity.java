package com.bk.framework.extension.neo4j.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author BK
 * @version V2.0
 * @description: 应用实体
 * @date 2019-06-19 23:48
 */
@NodeEntity(label = "APP")
@NoArgsConstructor
@Data
public class AppEntity extends BaseEntity {
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
