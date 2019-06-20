package com.bk.framework.extension.neo4j.service;

import com.bk.framework.extension.neo4j.model.dto.EnvDto;
import com.bk.framework.extension.neo4j.model.dto.MachineAppDto;
import com.bk.framework.extension.neo4j.model.dto.MachineDto;
import com.bk.framework.extension.neo4j.model.dto.SpaceQueryRequest;

import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 空间环境服务接口
 * @date 2019-06-19 23:23
 */
public interface SpaceEnvService {

    /**
     * 查询空间中环境的列表
     *
     * @param request
     * @return
     */
    List<EnvDto> findSpaceEnvList(SpaceQueryRequest request);

    /**
     * 查询环境上的服务器列表
     *
     * @param request request
     * @return
     */
    List<MachineDto> findEnvMachineList(SpaceQueryRequest request);

    /**
     * 查询服务器上应用的列表
     *
     * @param request request
     * @return
     */
    List<MachineAppDto> findMachineAppList(SpaceQueryRequest request);

}
