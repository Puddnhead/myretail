package com.myretail.service.client;

/**
 * Service for fetching product names
 *
 * Created by MVW on 7/3/2018.
 */
public interface ProductNameService {

    /**
     * Get the product name for a given product id
     *
     * @param productId product id
     * @return the product name or null
     */
    String getProductName(String productId);
}
