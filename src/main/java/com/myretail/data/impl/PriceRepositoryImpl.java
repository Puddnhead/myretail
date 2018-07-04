package com.myretail.data.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.myretail.data.api.PriceRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.mongodb.client.model.Filters.eq;

/**
 * Implementation class for {@link com.myretail.data.api.PriceRepository}
 *
 * Created by MVW on 7/3/2018.
 */
@Component
public class PriceRepositoryImpl implements PriceRepository {

    /**
     * Client that provides connection to the mongo db
     */
    private MongoClient mongoClient;

    /**
     * Mongo Database name
     */
    @Value("${mongo.db}")
    private String dbName;

    /**
     * Mongo Collection name
     */
    @Value("${mongo.collection}")
    private String collectionName;

    /**
     * Property name for the price field in mongo
     */
    static final String PRICE_PROPERTY = "price";

    /**
     * Property name for the product id field in mongo
     */
    static final String PRODUCT_ID_PROPERTY = "product_id";

    public PriceRepositoryImpl(@NonNull MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    /**
     * @see com.myretail.data.api.PriceRepository#getProductPrice(String)
     */
    @Override
    public BigDecimal getProductPrice(String productId) {
        BigDecimal price = null;

        MongoDatabase db = mongoClient.getDatabase(dbName);
        MongoCollection<Document> collection = db.getCollection(collectionName);
        MongoCursor<Document> documents = collection.find(eq(PRODUCT_ID_PROPERTY, productId)).iterator();

        if (documents.hasNext()) {
            price = new BigDecimal(documents.next().getDouble(PRICE_PROPERTY));
        }

        return price;
    }
}
