package com.bk.framework.extension.neo4j.repository;

import com.bk.framework.extension.neo4j.model.entity.SpaceEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author BK
 * @version V2.0
 * @description: 空间仓库
 * @date 2019-06-19 23:53
 */
@Repository
public interface SpaceRepository extends Neo4jRepository<SpaceEntity, Long> {

    /**
     * 查询空间信息
     *
     * @param projectCode 项目ID
     * @param accessKey   接入编码
     * @return
     */

    @Query("match x= (p:SPACE) -[:include]->(:ENV) where p.projectCode = {0} and p.accessKey ={1}  return x ")
    SpaceEntity findSpace(String projectCode, String accessKey);

}
