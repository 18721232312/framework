package com.bk.framework.extension.neo4j.controller;

import com.bk.framework.extension.neo4j.model.dto.MachineDto;
import com.bk.framework.extension.neo4j.model.dto.SpaceQueryRequest;
import com.bk.framework.extension.neo4j.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 服务器WEB接口
 * @date 2019-06-18 00:12
 */
@RestController
@RequestMapping("/space/env/machine")
public class MachineController {
    @Autowired
    private MachineService machineService;

    /**
     * 查找安装了某些应用的服务器
     *
     * @param request
     * @return
     */
    @GetMapping("/findMachines")
    public List<MachineDto> findMachines(SpaceQueryRequest request) {
        return machineService.findByCondition(request);
    }

}
