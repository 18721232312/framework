package com.bk.framework.extension.neo4j.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author BK
 * @version V2.0
 * @description: 环境DTO
 * @date 2019-06-19 15:55
 */
@Data
@Builder
public class EnvDto {
    /**
     * 主键
     */
    public Long id;
    /**
     * title
     */
    public String title;
    /**
     * 编码
     */
    public String code;
    /**
     * 类型
     */
    public String type;

}
