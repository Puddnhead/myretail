package com.myretail.data.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.myretail.data.api.PriceRepository;
import com.myretail.util.Outcome;
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

        MongoCollection<Document> collection = getCollection();
        MongoCursor<Document> documents = collection.find(eq(PRODUCT_ID_PROPERTY, productId)).iterator();

        if (documents.hasNext()) {
            price = new BigDecimal(documents.next().getDouble(PRICE_PROPERTY));
        }

        return price;
    }

    /**
     * @see com.myretail.data.api.PriceRepository#updatePrice(String, BigDecimal)
     */
    @Override
    public Outcome updatePrice(String productId, BigDecimal price) {
        MongoCollection<Document> collection = getCollection();
        UpdateResult result = collection.updateOne(eq(PRODUCT_ID_PROPERTY, productId),
                new Document("$set", new Document(PRICE_PROPERTY, price.doubleValue())));

        if (result.getMatchedCount() > 0) {
            return Outcome.SUCCESS;
        }

        return Outcome.FAILURE;
    }

    /**
     * Utility method to fetch the product collection
     *
     * @return the product collection
     */
    private MongoCollection<Document> getCollection() {
        MongoDatabase db = mongoClient.getDatabase(dbName);
        return db.getCollection(collectionName);
    }
}
