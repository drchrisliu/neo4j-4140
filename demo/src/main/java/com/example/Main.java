package com.example;

public class Main {
    public static void main(String[] args) {
        Neo4jConnectionFactory connectionFactory = new Neo4jConnectionFactory("bolt://localhost:7687", "neo4j", "neo4j");

        Neo4jCRUDOperations operations = new Neo4jCRUDOperations(connectionFactory);

        // Create a new person
        operations.createPerson("Alice", 30);

        // Read a person
        Person alice = operations.readPerson("Alice");
        System.out.println(alice.getName() + " is " + alice.getAge() + " years old.");

        // Update a person's age
        operations.updatePersonAge("Alice", 31);

        // Delete a person
        operations.deletePerson("Alice");

        connectionFactory.close();
    }

}
