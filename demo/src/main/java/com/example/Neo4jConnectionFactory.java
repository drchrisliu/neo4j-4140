package com.example;
import org.neo4j.driver.*;

public class Neo4jConnectionFactory {

    private final Driver driver;

    public Neo4jConnectionFactory(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public Session getSession() {
        return driver.session();
    }

    public void close() {
        driver.close();
    }
}
