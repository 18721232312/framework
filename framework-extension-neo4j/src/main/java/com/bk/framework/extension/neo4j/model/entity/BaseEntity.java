package com.bk.framework.extension.neo4j.model.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

/**
 * @author BK
 * @version V2.0
 * @description: base实体
 * @date 2019-06-19 14:45
 */
@Data
public class BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
    protected Long id;

    /**
     * title
     */
    protected String title;
    /**
     * 编码
     */
    protected String code;
    /**
     * 类型
     */
    protected String type;
}
