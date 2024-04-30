package com.example;

import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

public class Neo4jCRUDOperations {
    private final Neo4jConnectionFactory connectionFactory;

    public Neo4jCRUDOperations(Neo4jConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    // Create operation
    public void createPerson(String name, int age) {
        try (Session session = connectionFactory.getSession()) {
            String query = "CREATE (p:Person {name: $name, age: $age})";
            session.run(query, Values.parameters("name", name, "age", age));
        }
    }

    // Read operation
    public Person readPerson(String name) {
        try (Session session = connectionFactory.getSession()) {
            String query = "MATCH (p:Person {name: $name}) RETURN p.name AS name, p.age AS age";
            Result result = session.run(query, Values.parameters("name", name));
            if (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                return new Person(record.get("name").asString(), record.get("age").asInt());
            }
        }
        return null;
    }

    // Update operation
    public void updatePersonAge(String name, int newAge) {
        try (Session session = connectionFactory.getSession()) {
            String query = "MATCH (p:Person {name: $name}) SET p.age = $newAge";
            session.run(query, Values.parameters("name", name, "newAge", newAge));
        }
    }

    // Delete operation
    public void deletePerson(String name) {
        try (Session session = connectionFactory.getSession()) {
            String query = "MATCH (p:Person {name: $name}) DETACH DELETE p";
            session.run(query, Values.parameters("name", name));
        }
    }

}