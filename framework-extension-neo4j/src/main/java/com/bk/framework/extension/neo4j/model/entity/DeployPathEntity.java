package com.bk.framework.extension.neo4j.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author BK
 * @version V2.0
 * @description: 部署路径实体
 * @date 2019-06-19 21:33
 */
@NodeEntity(label = "DEPLOY_PATH")
@NoArgsConstructor
@Data
public class DeployPathEntity extends BaseEntity {

}
