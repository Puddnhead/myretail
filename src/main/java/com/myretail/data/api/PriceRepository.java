package com.myretail.data.api;

import java.math.BigDecimal;

/**
 * Repository for fetching product prices
 *
 * Created by MVW on 7/3/2018.
 */
public interface PriceRepository {

    /**
     * Fetch a price for a given product
     *
     * @param productId the product id
     * @return a price or null if the price cannot be found
     */
    BigDecimal getProductPrice(String productId);
}
