package com.bk.framework.extension.neo4j.repository;

import com.bk.framework.extension.neo4j.model.entity.MachineEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 服务器仓库
 * @date 2019-06-19 23:53
 */
@Repository
public interface MachineRepository extends Neo4jRepository<MachineEntity, Long> {

    /**
     * 查询接入方下，某环境下装了指定应用的服务器信息
     *
     * @param projectCode 项目编码
     * @param accessKey   准入KEY
     * @param envType     环境类型
     * @param appType     应用类型
     * @return
     */
    @Query(value = "match x= (p:SPACE) -[:include]-> (e:ENV) -[:has]-> (m:MACHINE) -[:setup]->(a:APP) where p.projectCode = {0} and p.accessKey ={1} AND e.type = {2}  and a.type = {3} return x ")
    List<MachineEntity> queryByAppAndEnv(String projectCode, String accessKey, String envType, String appType);
}
