package com.bk.framework.extension.neo4j.repository;

import com.bk.framework.extension.neo4j.model.entity.AppEntity;
import com.bk.framework.extension.neo4j.model.entity.EnvEntity;
import com.bk.framework.extension.neo4j.model.entity.MachineEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 环境仓库
 * @date 2019-06-19 23:53
 */
@Repository
public interface EnvRepository extends Neo4jRepository<EnvEntity, Long> {
    /**
     * 查询环境列表
     *
     * @param projectCode 项目编码
     * @param accessKey   接入key
     * @return
     */
    @Query(value = "match x= (p:SPACE) -[:include]->(e:ENV) where p.projectCode = {0} and p.accessKey ={1}  return e ", countQuery = "match x= (p:SPACE) -[:include]->(e:ENV) where p.projectCode = {0} and p.accessKey ={1}  return count(e)  ")
    List<EnvEntity> findEnvList(String projectCode, String accessKey);

    /**
     * 查询环境的服务器列表
     *
     * @param projectCode 项目编码
     * @param accessKey   接入key
     * @param envId       环境ID
     * @return
     */
    @Query(value = "match  x = (p:SPACE) -[:include]-> (e:ENV) -[:has]->(m:MACHINE) where p.projectCode = {0} and p.accessKey ={1} AND id(e) = {2}  return x ")
    List<MachineEntity> findMachineList(String projectCode, String accessKey, Long envId);

    /**
     * 查询服务器上的应用列表
     *
     * @param projectCode 项目编码
     * @param accessKey   接入key
     * @param machineId   服务器ID
     * @return
     */
    @Query(value = "match x= (p:SPACE) -[:include]-> (e:ENV) -[:has]-> (m:MACHINE) -[:setup]->(a:APP) where p.projectCode = {0} and p.accessKey ={1} AND id(m)= {2}  return x ")
    List<AppEntity> findMachineAppList(String projectCode, String accessKey, Long machineId);

}
