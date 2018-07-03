package com.myretail.service.client;

import com.myretail.domain.Product;

/**
 * Service for CRUD operations on products
 *
 * Created by MVW on 7/2/2018.
 */
public interface ProductService {

    /**
     * Fetch a product with a given id
     *
     * @param productId product id
     * @return product with the given id or null
     */
    Product getProduct(String productId);
}
