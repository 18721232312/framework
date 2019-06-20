package com.bk.framework.extension.neo4j.utils;

import com.bk.framework.extension.neo4j.model.dto.EnvDto;
import com.bk.framework.extension.neo4j.model.dto.MachineAppDto;
import com.bk.framework.extension.neo4j.model.dto.MachineDto;
import com.bk.framework.extension.neo4j.model.entity.AppEntity;
import com.bk.framework.extension.neo4j.model.entity.EnvEntity;
import com.bk.framework.extension.neo4j.model.entity.MachineEntity;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BK
 * @version V2.0
 * @description: 实体工具类转换
 * @date 2019-06-20 14:31
 */
public class EntityDtoConverter {
    /**
     * 转换成服务器DTO列表
     *
     * @param entityList 服务器实体列表
     * @return
     */
    public static List<MachineDto> convertToMachineDtoList(List<MachineEntity> entityList) {
        if (!CollectionUtils.isEmpty(entityList)) {
            return entityList.stream().map(EntityDtoConverter::convertMachineToDto).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    /**
     * 转换成服务器DTO
     *
     * @param entity 服务器实体
     * @return
     */
    public static MachineDto convertMachineToDto(MachineEntity entity) {
        return MachineDto.builder().id(entity.getId()).title(entity.getTitle()).code(entity.getCode()).type(entity.getType()).ip(entity.getIp()).status(entity.getStatus()).system(entity.getSystem()).build();
    }

    /**
     * 转换成应用DTO列表
     *
     * @param entityList 应用实体列表
     * @return
     */
    public static List<MachineAppDto> convertToAppDtoList(List<AppEntity> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return Lists.newArrayList();
        }
        return entityList.stream().map(EntityDtoConverter::convertAppToDto).collect(Collectors.toList());
    }

    /**
     * 转换成应用DTO
     *
     * @param entity 应用实体
     * @return
     */
    public static MachineAppDto convertAppToDto(AppEntity entity) {
        return MachineAppDto.builder().id(entity.getId()).title(entity.getTitle()).code(entity.getCode()).type(entity.getType()).version(entity.getVersion()).appType(entity.getAppType()).domain(entity.getDomain()).role(entity.getRole()).build();
    }

    /**
     * 转换成环境DTO列表
     *
     * @param entityList 环境实体列表
     * @return
     */
    public static List<EnvDto> convertToEnvDtoList(List<EnvEntity> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return Lists.newArrayList();
        }
        return entityList.stream().map(EntityDtoConverter::convertEnvToDto).collect(Collectors.toList());
    }

    /**
     * 转换成环境DTO
     *
     * @param entity 环境实体
     * @return
     */
    public static EnvDto convertEnvToDto(EnvEntity entity) {
        return EnvDto.builder().id(entity.getId()).title(entity.getTitle()).code(entity.getCode()).type(entity.getType()).build();
    }

}
