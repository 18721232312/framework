package com.bk.framework.extension.neo4j.repository;

import com.bk.framework.extension.neo4j.model.entity.DeployPathEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author BK
 * @version V2.0
 * @description: 部署路径仓库
 * @date 2019-06-19 23:53
 */
@Repository
public interface DeployPathRepository extends Neo4jRepository<DeployPathEntity, Long> {
}
