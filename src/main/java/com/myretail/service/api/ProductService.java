package com.myretail.service.api;

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
     * @return product with the given id
     * @throws com.myretail.service.exception.ProductNotFoundException if a valid product cannot be found
     */
    Product getProduct(String productId);

    /**
     * Update the price of a given product. Ignores any changes to the product name.
     *
     * @param product the product to update including productId and price
     * @return the updated entity
     */
    Product updatePrice(Product product);
}
