package com.wilklow.data.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.wilklow.data.api.PriceRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.mongodb.client.model.Filters.eq;

/**
 * Implementation class for {@link com.wilklow.data.api.PriceRepository}
 *
 * Created by MVW on 7/3/2018.
 */
@Component
public class PriceRepositoryImpl implements PriceRepository {

    private MongoClient mongoClient;

    @Value("${mongo.db}")
    private String dbName;

    @Value("${mongo.collection}")
    private String collectionName;

    public PriceRepositoryImpl(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public BigDecimal getProductPrice(String productId) {
        BigDecimal price = null;

        MongoDatabase db = mongoClient.getDatabase(dbName);
        MongoCollection<Document> collection = db.getCollection(collectionName);
        MongoCursor<Document> documents = collection.find(eq("productId", productId)).limit(1).iterator();

        if (documents.hasNext()) {
            price = new BigDecimal(documents.next().getDouble("price"));
        }

        return price;
    }
}
