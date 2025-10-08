package com.napier.sem;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class App
{
    public static void main(String[] args)
    {
        // Connect to MongoDB (modern driver)
        try (MongoClient mongoClient = MongoClients.create("mongodb://mongo-dbserver:27017")) {

            // Get a database - will create it when first used
            MongoDatabase database = mongoClient.getDatabase("mydb");

            // Get a collection from the database
            MongoCollection<Document> collection = database.getCollection("test");

            // Create a document to store
            Document doc = new Document("name", "Kevin Sim")
                    .append("class", "DevOps")
                    .append("year", "2024")
                    .append("result", new Document("CW", 95).append("EX", 85));

            // Add document to collection
            collection.insertOne(doc);

            // Check document in collection
            Document myDoc = collection.find().first();
            System.out.println(myDoc.toJson());
        }
    }
}
