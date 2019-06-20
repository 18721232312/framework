package com.bk.framework.extension.neo4j.controller;

import com.bk.framework.extension.neo4j.model.dto.SpaceDto;
import com.bk.framework.extension.neo4j.model.dto.SpaceQueryRequest;
import com.bk.framework.extension.neo4j.service.SpaceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BK
 * @version V2.0
 * @description: 空间WEB接口
 * @date 2019-06-18 00:12
 */
@RestController
@RequestMapping("space")
public class SpaceController {
    @Autowired
    private SpaceManageService spaceManageService;

    /**
     * 查询空间信息
     *
     * @param request
     * @return
     */
    @GetMapping("/findSpace")
    public SpaceDto findSpace(SpaceQueryRequest request) {
        return spaceManageService.findSpace(request);
    }

}
