package com.example.demo.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class AbstractMongoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    protected MongoClient mongoClient;

    protected static final String LOCAL_DEMO_MONGO_DB = "local_demo";

    protected MongoDatabase getDatabase() {
        return mongoClient.getDatabase(LOCAL_DEMO_MONGO_DB);
    }

    protected MongoCollection<Document> getCollection(String collectionName) {
        return mongoTemplate.getCollection(collectionName);
    }

}
