package com.bk.framework.extension.neo4j;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.neo4j.ogm.drivers.http.driver.HttpDriver;
import org.neo4j.ogm.exception.TransactionException;
import org.neo4j.ogm.transaction.AbstractTransaction;
import org.neo4j.ogm.transaction.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author BK
 * @version V2.0
 * @description: httpTransaction 扩展
 * @date 2019/7/8 10:33
 */
public class Neo4jHttpTransaction extends AbstractTransaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpDriver.class);

    private final Neo4jHttpDriver driver;
    private final String url;

    public Neo4jHttpTransaction(TransactionManager transactionManager, Neo4jHttpDriver driver, String url,
                                Type type) {
        super(transactionManager);
        this.driver = driver;
        this.url = url;
        this.type = type;
    }


    @Override
    public void rollback() {

        try {
            if (transactionManager.canRollback()) {
                HttpDelete request = new HttpDelete(url);
                request.setHeader(new BasicHeader("X-WRITE", driver.readOnly() ? "0" : "1"));
                driver.executeHttpRequest(request);
            }
        } catch (Exception e) {
            LOGGER.warn(e.getLocalizedMessage());
        } finally {
            super.rollback(); // must always be done to keep extension depth correct
        }
    }

    @Override
    public void commit() {

        try {
            if (transactionManager.canCommit()) {
                HttpPost request = new HttpPost(url + "/commit");
                request.setHeader(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
                request.setHeader(new BasicHeader("X-WRITE", driver.readOnly() ? "0" : "1"));
                driver.executeHttpRequest(request);
            }
        } catch (Exception e) {
            throw new TransactionException(e.getLocalizedMessage(), e);
        } finally {
            super.commit(); // must always be done to keep extension depth correct
        }
    }

    public String url() {
        return url;
    }
}
