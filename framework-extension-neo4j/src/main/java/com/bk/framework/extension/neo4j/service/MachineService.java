package com.bk.framework.extension.neo4j.service;

import com.bk.framework.extension.neo4j.model.dto.MachineDto;
import com.bk.framework.extension.neo4j.model.dto.SpaceQueryRequest;

import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 服务器服务接口
 * @date 2019-06-19 23:23
 */
public interface MachineService {

    /**
     * 查询空间中环境的列表
     *
     * @param request
     * @return
     */
    List<MachineDto> findByCondition(SpaceQueryRequest request);

}
