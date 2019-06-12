package com.bk.framework.extension.neo4j;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.ogm.drivers.http.request.HttpRequest;
import org.neo4j.ogm.model.GraphModel;
import org.neo4j.ogm.model.GraphRowListModel;
import org.neo4j.ogm.model.RestModel;
import org.neo4j.ogm.model.RowModel;
import org.neo4j.ogm.request.*;
import org.neo4j.ogm.response.EmptyResponse;
import org.neo4j.ogm.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author BK
 * @version V2.0
 * @description: Neo4j HttpRequest适配 ，使可支持动态关系
 * @date 2019/4/29 19:40
 */
@Slf4j
public class Neo4jHttpRequestAdaptor implements Request {
    private HttpRequest httpRequest;

    Neo4jHttpRequestAdaptor(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Override
    public Response<GraphModel> execute(GraphModelRequest query) {
        GraphModelRequest request = new Neo4jDefaultRequest(query);
        return httpRequest.execute(request);
    }

    @Override
    public Response<RowModel> execute(RowModelRequest query) {
        if (query.getStatement().length() == 0) {
            return new EmptyResponse();
        } else {
            RowModelRequest request = new Neo4jDefaultRequest(query);
            return httpRequest.execute(request);
        }
    }

    @Override
    public Response<RowModel> execute(DefaultRequest query) {
        org.neo4j.ogm.request.DefaultRequest q = () -> {
            List<Statement> list = new ArrayList<>();
            for (Statement k : query.getStatements()) {
                list.add(new Neo4jDefaultRequest(k));
            }
            return list;
        };
        return httpRequest.execute(q);
    }

    @Override
    public Response<GraphRowListModel> execute(GraphRowListModelRequest query) {
        GraphRowListModelRequest request = new Neo4jDefaultRequest(query);
        return httpRequest.execute(request);
    }

    @Override
    public Response<RestModel> execute(RestModelRequest query) {
        if (query.getStatement().length() == 0) {
            return new EmptyResponse();
        } else {
            RestModelRequest request = new Neo4jDefaultRequest(query);
            return httpRequest.execute(request);
        }
    }

    private class Neo4jDefaultRequest implements GraphModelRequest, RowModelRequest, GraphRowListModelRequest, RestModelRequest {
        private Statement query;

        Neo4jDefaultRequest(Statement query) {
            this.query = query;
        }

        @Override
        public String getStatement() {
            return Neo4jExtUtils.processCqlRelation(query.getStatement(), query.getParameters());
        }

        @Override
        public Map<String, Object> getParameters() {
            return query.getParameters();
        }

        @Override
        public String[] getResultDataContents() {
            return query.getResultDataContents();
        }

        @Override
        public boolean isIncludeStats() {
            return query.isIncludeStats();
        }

        @Override
        public Optional<OptimisticLockingConfig> optimisticLockingConfig() {
            return query.optimisticLockingConfig();
        }

    }
}
