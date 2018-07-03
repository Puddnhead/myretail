package com.myretail.data;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration that creates a MongoDB Client
 *
 * Created by MVW on 7/3/2018.
 */
@Configuration
public class MongoDBConnectionProvider {

    @Value("${mongo.host:localhost}")
    private String host;

    @Value("${mongo.port:27017}")
    private int port;

    @Bean
    MongoClient mongoClient() {
        return new MongoClient(host, port);
    }
}
