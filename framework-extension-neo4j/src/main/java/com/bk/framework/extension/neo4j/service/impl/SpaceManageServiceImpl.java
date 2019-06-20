package com.bk.framework.extension.neo4j.service.impl;

import com.bk.framework.extension.neo4j.model.dto.SpaceDto;
import com.bk.framework.extension.neo4j.model.dto.SpaceQueryRequest;
import com.bk.framework.extension.neo4j.model.entity.SpaceEntity;
import com.bk.framework.extension.neo4j.repository.SpaceRepository;
import com.bk.framework.extension.neo4j.service.SpaceManageService;
import com.bk.framework.extension.neo4j.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author BK
 * @version V2.0
 * @description: 空间管理接口实现
 * @date 2019-06-19 00:11
 */
@Service
public class SpaceManageServiceImpl implements SpaceManageService {
    @Autowired
    private SpaceRepository spaceRepository;

    @Override
    public SpaceDto findSpace(SpaceQueryRequest request) {
        AssertUtils.assertNotBlank(request.getAccessKey(), "接入key不能为空");
        AssertUtils.assertNotBlank(request.getProjectCode(), "项目不能为空");
        SpaceEntity entity = spaceRepository.findSpace(request.getProjectCode(), request.getAccessKey());
        return convertSpaceToDto(entity);
    }

    @Override
    public SpaceDto findSpaceDetach(SpaceQueryRequest request) {
        SpaceEntity entity = spaceRepository.findSpace(request.getProjectCode(), request.getAccessKey());
        if (entity != null) {
            Optional<SpaceEntity> optional = spaceRepository.findById(entity.getId());
            if (optional.isPresent()) {
                return convertSpaceToDto(optional.get());
            }
        }
        return null;
    }

    private SpaceDto convertSpaceToDto(SpaceEntity entity) {
        if (entity != null) {
            return SpaceDto.builder().id(entity.getId()).title(entity.getTitle()).code(entity.getCode()).type(entity.getType()).accessKey(entity.getAccessKey()).projectCode(entity.getProjectCode()).description(entity.getDescription()).build();
        }
        return SpaceDto.builder().build();
    }
}
