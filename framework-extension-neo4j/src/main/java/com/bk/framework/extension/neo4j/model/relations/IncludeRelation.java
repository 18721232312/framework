package com.bk.framework.extension.neo4j.model.relations;

import com.bk.framework.extension.neo4j.model.entity.EnvEntity;
import com.bk.framework.extension.neo4j.model.entity.SpaceEntity;
import lombok.Data;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.model.Property;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @date 2019-06-20 00:08
 */
@Data
@RelationshipEntity(type = "include")
public class IncludeRelation {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private SpaceEntity spaceEntity;

    @EndNode
    private EnvEntity envEntity;

    private Property property;
}
