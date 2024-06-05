package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MongoDBConnectionTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void testConnection() {
        if (mongoTemplate.getDb().getName() != null) {
            System.out.println("Successfully connected to MongoDB!"+ mongoTemplate.getDb().getName() );
        } else {
            System.out.println("Failed to connect to MongoDB!");
        }
    }
}
