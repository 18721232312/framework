package com.bk.framework.extension.neo4j.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 空间实体
 * @date 2019-06-19 23:46
 */
@NodeEntity(label = "SPACE")
@NoArgsConstructor
@Data
public class SpaceEntity extends BaseEntity {
    /**
     * 接入KEY
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
    /**
     * include关系
     */
    @Relationship(type = "include")
    private List<EnvEntity> includeList;
}
