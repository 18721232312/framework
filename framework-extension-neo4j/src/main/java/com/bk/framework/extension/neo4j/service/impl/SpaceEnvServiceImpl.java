package com.bk.framework.extension.neo4j.service.impl;

import com.bk.framework.extension.neo4j.model.dto.EnvDto;
import com.bk.framework.extension.neo4j.model.dto.MachineAppDto;
import com.bk.framework.extension.neo4j.model.dto.MachineDto;
import com.bk.framework.extension.neo4j.model.dto.SpaceQueryRequest;
import com.bk.framework.extension.neo4j.model.entity.AppEntity;
import com.bk.framework.extension.neo4j.model.entity.EnvEntity;
import com.bk.framework.extension.neo4j.model.entity.MachineEntity;
import com.bk.framework.extension.neo4j.repository.EnvRepository;
import com.bk.framework.extension.neo4j.service.SpaceEnvService;
import com.bk.framework.extension.neo4j.utils.AssertUtils;
import com.bk.framework.extension.neo4j.utils.EntityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 空间环境服务实现
 * @date 2019-06-19 23:24
 */
@Service
public class SpaceEnvServiceImpl implements SpaceEnvService {
    @Autowired
    private EnvRepository envRepository;

    @Override
    public List<EnvDto> findSpaceEnvList(SpaceQueryRequest request) {
        AssertUtils.assertNotBlank(request.getAccessKey(), "接入key不能为空");
        AssertUtils.assertNotBlank(request.getProjectCode(), "项目不能为空");
        List<EnvEntity> entityList = envRepository.findEnvList(request.getProjectCode(), request.getAccessKey());
        return EntityDtoConverter.convertToEnvDtoList(entityList);
    }

    @Override
    public List<MachineDto> findEnvMachineList(SpaceQueryRequest request) {
        AssertUtils.assertNotBlank(request.getAccessKey(), "接入key不能为空");
        AssertUtils.assertNotBlank(request.getProjectCode(), "项目不能为空");
        AssertUtils.assertIsLong(request.getId(), "请求ID格式不正确");
        List<MachineEntity> entityList = envRepository.findMachineList(request.getProjectCode(), request.getAccessKey(), request.getId());
        return EntityDtoConverter.convertToMachineDtoList(entityList);
    }

    @Override
    public List<MachineAppDto> findMachineAppList(SpaceQueryRequest request) {
        AssertUtils.assertNotBlank(request.getAccessKey(), "接入key不能为空");
        AssertUtils.assertNotBlank(request.getProjectCode(), "项目不能为空");
        AssertUtils.assertIsLong(request.getId(), "请求ID格式不正确");
        List<AppEntity> entityList = envRepository.findMachineAppList(request.getProjectCode(), request.getAccessKey(), request.getId());
        return EntityDtoConverter.convertToAppDtoList(entityList);
    }
}
