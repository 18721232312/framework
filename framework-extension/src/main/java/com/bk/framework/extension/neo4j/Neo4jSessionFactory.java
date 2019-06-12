package com.bk.framework.extension.neo4j;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.driver.Driver;
import org.neo4j.ogm.session.SessionFactory;

import static org.neo4j.ogm.config.AutoIndexMode.NONE;

/**
 * @author BK
 * @version V2.0
 * @description: neo4j session factory 扩展，支持动态关系
 * @date 2019/4/30 9:33
 */
public class Neo4jSessionFactory extends SessionFactory {

    public Neo4jSessionFactory(Configuration configuration, String... packages) {
        super(buildDriver(configuration), packages);
        if (configuration.getAutoIndex() != NONE) {
            runAutoIndexManager(configuration);
        }
    }

    private static Driver buildDriver(Configuration configuration) {
        final Driver driver = new Neo4jHttpDriver();
        driver.configure(configuration);
        return driver;
    }

}
