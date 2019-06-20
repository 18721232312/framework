package com.bk.framework.extension.neo4j.repository;

import com.bk.framework.extension.neo4j.model.entity.AppEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author BK
 * @version V2.0
 * @description: 应用仓库
 * @date 2019-06-19 23:53
 */
@Repository
public interface AppRepository extends Neo4jRepository<AppEntity, Long> {
}
