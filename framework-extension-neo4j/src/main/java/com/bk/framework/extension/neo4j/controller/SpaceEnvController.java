package com.bk.framework.extension.neo4j.controller;

import com.bk.framework.extension.neo4j.model.dto.EnvDto;
import com.bk.framework.extension.neo4j.model.dto.MachineAppDto;
import com.bk.framework.extension.neo4j.model.dto.MachineDto;
import com.bk.framework.extension.neo4j.model.dto.SpaceQueryRequest;
import com.bk.framework.extension.neo4j.service.SpaceEnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 空间WEB接口
 * @date 2019-06-18 00:12
 */
@RestController
@RequestMapping("/space/env")
public class SpaceEnvController {
    @Autowired
    private SpaceEnvService spaceEnvService;

    /**
     * 查询空间中的环境列表
     *
     * @param request
     * @return
     */
    @GetMapping("/findEnvList")
    public List<EnvDto> findEvnList(SpaceQueryRequest request) {
        return spaceEnvService.findSpaceEnvList(request);
    }

    /**
     * 查询环境中服务器列表
     *
     * @param request
     * @return
     */
    @GetMapping("/findEnvMachineList")
    public List<MachineDto> findEvnMachineList(SpaceQueryRequest request) {
        return spaceEnvService.findEnvMachineList(request);
    }

    /**
     * 查询服务器上应用的列表
     *
     * @param request request
     * @return
     */
    @GetMapping("/findMachineAppList")
    public List<MachineAppDto> findMachineAppList(SpaceQueryRequest request) {
        return spaceEnvService.findMachineAppList(request);
    }

}
