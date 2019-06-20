package com.bk.framework.extension.neo4j.service.impl;

import com.bk.framework.extension.neo4j.model.dto.MachineDto;
import com.bk.framework.extension.neo4j.model.dto.SpaceQueryRequest;
import com.bk.framework.extension.neo4j.model.entity.MachineEntity;
import com.bk.framework.extension.neo4j.repository.MachineRepository;
import com.bk.framework.extension.neo4j.service.MachineService;
import com.bk.framework.extension.neo4j.utils.AssertUtils;
import com.bk.framework.extension.neo4j.utils.EntityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 服务器服务接口实现
 * @date 2019-06-20 14:16
 */
@Service
public class MachineServiceImpl implements MachineService {
    @Autowired
    private MachineRepository machineRepository;

    @Override
    public List<MachineDto> findByCondition(SpaceQueryRequest request) {
        AssertUtils.assertNotBlank(request.getAccessKey(), "接入key不能为空");
        AssertUtils.assertNotBlank(request.getProjectCode(), "项目不能为空");
        AssertUtils.assertNotBlank(request.getAppType(), "app类型不能为空");
        AssertUtils.assertNotBlank(request.getEnvType(), "环境类型不能为空");
        List<MachineEntity> entityList = machineRepository.queryByAppAndEnv(request.getProjectCode(), request.getAccessKey(), request.getEnvType(), request.getAppType());
        return EntityDtoConverter.convertToMachineDtoList(entityList);
    }

}
