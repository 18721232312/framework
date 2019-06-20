package com.bk.framework.extension.neo4j.service;

import com.bk.framework.extension.neo4j.model.dto.SpaceDto;
import com.bk.framework.extension.neo4j.model.dto.SpaceQueryRequest;

/**
 * @author BK
 * @version V2.0
 * @description: 空间管理服务接口
 * @date 2019-06-19 00:09
 */
public interface SpaceManageService {
    /**
     * 查询空间信息
     *
     * @param request request
     * @return
     */
    SpaceDto findSpace(SpaceQueryRequest request);

    /**
     * 查询空间信息
     *
     * @param request request
     * @return
     */
    SpaceDto findSpaceDetach(SpaceQueryRequest request);

}
