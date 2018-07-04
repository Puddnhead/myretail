package com.myretail.data.api;

import com.myretail.domain.Price;
import com.myretail.util.Outcome;

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
    Price getProductPrice(String productId);

    /**
     * Update the price of a product
     *
     * @param productId product id
     * @param price price
     * @return SUCCESS if the update succeeded else FAILURE
     */
    Outcome updatePrice(String productId, Price price);
}
