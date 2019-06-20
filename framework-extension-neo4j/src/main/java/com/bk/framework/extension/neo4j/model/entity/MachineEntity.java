package com.bk.framework.extension.neo4j.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 服务器实体
 * @date 2019-06-19 21:33
 */
@NodeEntity(label = "MACHINE")
@NoArgsConstructor
@Data
public class MachineEntity extends BaseEntity {
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

    @Relationship("setup")
    private List<AppEntity> appEntityList;

}
